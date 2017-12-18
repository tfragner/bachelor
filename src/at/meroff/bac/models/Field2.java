package at.meroff.bac.models;

import at.meroff.bac.Cosine;
import com.sun.javafx.geom.Vec2d;
import javafx.util.Pair;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Field2 {

    String name;
    Set<Subject> subjects = new HashSet<>();
    Set<Task> tasks = new HashSet<>();
    Set<Transfer> transfers = new HashSet<>();
    Set<Pair<Subject, Set<Pair<Task, Vec2d>>>> vectorSummary;

    public Field2() {

    }

    /**
     * Add a card and put it into the right Set
     * @param card
     * @return
     */
    boolean addCard(Card card) {
        if (card instanceof Subject) {
            subjects.add((Subject)card);
        } else if (card instanceof Task) {
            tasks.add((Task)card);
        } else if (card instanceof Transfer) {
            transfers.add((Transfer) card);
        } else {
            return false;
        }

        vectorSummary = getVectorsForAllSubjects();

        return true;
    }

    /**
     * Calculate all Vectors for each subject -> task relation
     */
    private Set<Pair<Subject, Set<Pair<Task, Vec2d>>>> getVectorsForAllSubjects() {

        return subjects.stream()
                .map(subject -> {
                    return new Pair<>(subject, getVectorsForSubject(subject));
                }).collect(Collectors.toSet());

    }

    /**
     * Calculate all Vectors for each subject -> task relation
     */
    private Set<Pair<Task, Vec2d>> getVectorsForSubject(Subject searchSubject) {

        return tasks.stream()
                            .map(task -> new Pair<>(task, new Vec2d(task.getCenter().x - searchSubject.getCenter().x, task.getCenter().y - searchSubject.getCenter().y)))
                            .collect(Collectors.toSet());

    }

    public Rectangle getSubjectsBoundaries() {
        double x1 = subjects.stream().mapToDouble(Card::getSmallestX).min().getAsDouble();
        double y1 = subjects.stream().mapToDouble(Card::getSmallestY).min().getAsDouble();
        double x2 = subjects.stream().mapToDouble(Card::getBiggestX).max().getAsDouble();
        double y2 = subjects.stream().mapToDouble(Card::getBiggestY).max().getAsDouble();

        return new Rectangle((int)x1, (int)y1, (int)x2-(int)x1, (int)y2-(int)y1);
    }

    public Rectangle getTasksBoundaries() {
        double x1 = tasks.stream().mapToDouble(Card::getSmallestX).min().getAsDouble();
        double y1 = tasks.stream().mapToDouble(Card::getSmallestY).min().getAsDouble();
        double x2 = tasks.stream().mapToDouble(Card::getBiggestX).max().getAsDouble();
        double y2 = tasks.stream().mapToDouble(Card::getBiggestY).max().getAsDouble();

        return new Rectangle((int)x1, (int)y1, (int)x2-(int)x1, (int)y2-(int)y1);
    }

    public boolean isSubjectsIntersectingTasks() {
        return getSubjectsBoundaries().intersects(getTasksBoundaries());
    }

    public Rectangle getSubjectsIntersectionTasks() {
        return getSubjectsBoundaries().intersection(getTasksBoundaries());
    }

    public void findRelations3() {
        List<Subject> localSubjects;
        Set<Task> localTasks = this.tasks;
        Set<Task> usedTasks = new HashSet<>();

        localSubjects = subjects.stream()
                //sort subjects by smallest distance to nearest Task
                .sorted(Comparator.comparingDouble(o -> getTaskWithSmallestDistanceForSubject(o).getValue()))
                .collect(Collectors.toList());

        while (!localSubjects.isEmpty() && !localTasks.isEmpty()) {
            Subject currentSubject = localSubjects.get(0);
            System.out.println("Working on Subject: " + currentSubject);

            // get nearest Task
            List<Pair<Task, Double>> nearestList = getTasksOrderedByDistanceToSubject(currentSubject);
            nearestList.removeIf(taskDoublePair -> usedTasks.contains(taskDoublePair.getKey()));
            Task nearestTask = nearestList.get(0).getKey();

            System.out.println("Nearest Task: " + nearestTask);

            // get possible follow-ups
            List<Task> collect = tasks.stream()
                    .filter(task -> checkVectors(currentSubject, nearestTask, task, 0.0))
                    .map(task -> new Pair<>(task, Card.getDistance(currentSubject, task) - Card.getDistance(currentSubject, nearestTask)))
                    .filter(taskDoublePair -> taskDoublePair.getValue() > 0.0)
                    .sorted(Comparator.comparingDouble(Pair::getValue))
                    .map(Pair::getKey)
                    .filter(task -> getSimilarity(currentSubject, nearestTask, task) > 0.9)
                    .collect(Collectors.toList());

            for (Task task : collect) {
                System.out.println(task);
            }



            localSubjects.remove(currentSubject);
        }
    }

    boolean checkVectors(Subject subject, Task source, Task target, double epsilon) {
        Vec2d subjectToSource = new Vec2d(source.getCenter().x - subject.getCenter().x, target.getCenter().y - subject.getCenter().y);
        Vec2d subjectToTarget = new Vec2d(target.getCenter().x - subject.getCenter().x, target.getCenter().y - subject.getCenter().y);

        double similarity = Cosine.similarity(subjectToSource, subjectToTarget);

        return epsilon <= similarity && similarity <= 1.00;
    }

    double getSimilarity(Subject subject, Task source, Task target) {
        Vec2d subjectToSource = new Vec2d(source.getCenter().x - subject.getCenter().x, target.getCenter().y - subject.getCenter().y);
        Vec2d subjectToTarget = new Vec2d(target.getCenter().x - subject.getCenter().x, target.getCenter().y - subject.getCenter().y);

        return Cosine.similarity(subjectToSource, subjectToTarget);
    }

    private Pair<Task, Double> getTaskWithSmallestDistanceForSubject(Subject subject) {
        return tasks.stream()
                .map(task -> new Pair<>(task, Card.getDistance(subject, task)))
                .sorted(Comparator.comparing(Pair::getValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("test"));
    }

    private List<Pair<Task, Double>> getTasksOrderedByDistanceToSubject(Subject subject) {
        return tasks.stream()
                .map(task -> new Pair<>(task, Card.getDistance(subject, task)))
                .sorted(Comparator.comparing(Pair::getValue))
                .collect(Collectors.toList());
    }

    public void findRelations2() {
        // find Starting Task by smallest distance between Subjects and Tasks
        while (!subjects.isEmpty() && !tasks.isEmpty()) {
            Pair smallestDistance = findSmallestDistance();


            Subject startSubject = (Subject) smallestDistance.getKey();
            Task firstTask = (Task) smallestDistance.getValue();

            System.out.print("Starting Point for evaluation:  " + startSubject.id);
            System.out.print(" --> " + firstTask.id);

            // find all possible follow-ups
            Set<Pair<Task, Vec2d>> followUpsForSubject = getFollowUpsForSubject(startSubject);

            followUpsForSubject = followUpsForSubject.stream()
                    .filter(taskVec2dPair -> tasks.contains(taskVec2dPair.getKey()))
                    .collect(Collectors.toSet());

            boolean cont = true;

            while (cont) {

                // get Vector for first Task
                Task finalFirstTask = firstTask;
                Pair<Task, Vec2d> firstTaskVector = followUpsForSubject.stream()
                        .filter(taskVec2dPair -> taskVec2dPair.getKey().equals(finalFirstTask))
                        .findFirst()
                        .get();

                // find remaining Tasks
                Task finalFirstTask1 = firstTask;
                Set<Pair<Task, Vec2d>> remainingTasks = followUpsForSubject.stream()
                        .filter(taskVec2dPair -> !taskVec2dPair.getKey().equals(finalFirstTask1))
                        .collect(Collectors.toSet());

                // get Tasks and their values
                Task finalFirstTask3 = firstTask;
                Optional<Pair<Task, Double>> taskDoublePair = remainingTasks.stream()
                        .map(taskVec2dPair -> new Pair<>(taskVec2dPair.getKey(), new Pair<>(Cosine.similarity(taskVec2dPair.getValue(), firstTaskVector.getValue()), firstTaskVector.getValue().distanceSq(taskVec2dPair.getValue()))))
                        .filter(taskPairPair -> Card.getDistance(startSubject,taskPairPair.getKey()) - Card.getDistance(startSubject,finalFirstTask) > 0.0)
                        .filter(taskPairPair -> 0.85 <= taskPairPair.getValue().getKey() && taskPairPair.getValue().getKey() <= 1.00)
                        .filter(taskPairPair -> {
                            Vec2d sTo1 = new Vec2d(finalFirstTask3.getCenter().x - startSubject.getCenter().x, finalFirstTask3.getCenter().y - startSubject.getCenter().y);
                            Vec2d sTo2 = new Vec2d(taskPairPair.getKey().getCenter().x - finalFirstTask3.getCenter().x, taskPairPair.getKey().getCenter().y - finalFirstTask.getCenter().y);
                            double similarity = Cosine.similarity(sTo1, sTo2);
                            return !(-.4 <=  similarity && similarity <= +.5);
                        })
                        .map(taskPairPair -> new Pair<>(taskPairPair.getKey(), 1 / taskPairPair.getValue().getKey() * taskPairPair.getValue().getValue()))
                        .sorted(Comparator.comparing(Pair::getValue))
                        .findFirst();

                if (taskDoublePair.isPresent()) {

                    Pair<Task, Double> taskDoublePair1 = taskDoublePair.get();

                    Line2D inter = new Line2D.Double(startSubject.getCenter().x, startSubject.getCenter().y, taskDoublePair1.getKey().getCenter().x, taskDoublePair1.getKey().getCenter().y);

                    for (Subject subject : subjects) {
                        if (!subject.equals(startSubject)) {
                            Line2D l1 = new Line2D.Double(subject.p1.x, subject.p1.y, subject.p2.x, subject.p2.y);
                            Line2D l2 = new Line2D.Double(subject.p2.x, subject.p2.y, subject.p3.x, subject.p3.y);
                            Line2D l3 = new Line2D.Double(subject.p3.x, subject.p3.y, subject.p4.x, subject.p4.y);
                            Line2D l4 = new Line2D.Double(subject.p4.x, subject.p4.y, subject.p1.x, subject.p1.y);

                            if (l1.intersectsLine(inter) || l2.intersectsLine(inter) || l3.intersectsLine(inter) || l4.intersectsLine(inter)) {
                                cont = false;
                                tasks.remove(firstTask);
                            }
                        }
                    }

                    if(cont) {
                        System.out.print(" --> " + taskDoublePair1.getKey().id);
                        Task finalFirstTask2 = firstTask;
                        followUpsForSubject = followUpsForSubject.stream()
                                .filter(taskVec2dPair -> !taskVec2dPair.getKey().equals(finalFirstTask2))
                                .collect(Collectors.toSet());
                        tasks.remove(firstTask);
                        firstTask = taskDoublePair1.getKey();
                    }
                } else {
                    tasks.remove(firstTask);
                    cont = false;
                }

            }

            subjects.remove(startSubject);
            System.out.println();
        }
        System.out.println("Subjects left: " + subjects.size());
        System.out.println("Tasks left: " + tasks.size());
    }

    Set<Pair<Task, Vec2d>> getFollowUpsForSubject(Subject searchSubject) {
        return getVectorSummary().stream()
                .filter(subjectSetPair -> subjectSetPair.getKey().equals(searchSubject))
                .findFirst()
                .map(subjectSetPair -> subjectSetPair.getValue())
                .get();
    }

    public void findRelations() {

        Pair smallestDistance = findSmallestDistance();
        System.out.println(" -------- " + ((Card)smallestDistance.getKey()).id + " --> " + ((Card)smallestDistance.getValue()).id);

        Set<Pair<Subject, Set<Pair<Task, Vec2d>>>> vectorSummary = getVectorSummary();

        Set<Pair<Task, Vec2d>> tasksForSmallestDistanceToTask = vectorSummary.stream()
                .filter(subjectSetPair -> subjectSetPair.getKey().equals(smallestDistance.getKey()))
                .findFirst().get().getValue();

        Vec2d value1 = tasksForSmallestDistanceToTask.stream().filter(taskVec2dPair -> taskVec2dPair.getKey().equals(smallestDistance.getValue()))
                .findFirst().get().getValue();

        /*tasksForSmallestDistanceToTask.stream()
                .filter(taskVec2dPair -> !taskVec2dPair.getValue().equals(value1))
                .map(taskVec2dPair -> {
                    Pair<Task, Double> ret = new Pair<>(taskVec2dPair.getKey(),Cosine.similarity(taskVec2dPair.getValue(), value1));
                    return ret;
                })
                .sorted(Comparator.comparing(Pair::getValue, Comparator.reverseOrder()))
                .findFirst().ifPresent(taskDoublePair -> System.out.println(taskDoublePair));

        tasksForSmallestDistanceToTask.stream()
                .filter(taskVec2dPair -> !taskVec2dPair.getValue().equals(value1))
                .map(taskVec2dPair -> {
                    Pair<Task, Double> ret = new Pair<>(taskVec2dPair.getKey(),value1.distanceSq(taskVec2dPair.getValue()));
                    return ret;
                })
                .sorted(Comparator.comparing(Pair::getValue));
                //.forEach(taskDoublePair -> System.out.println(taskDoublePair));*/

        tasksForSmallestDistanceToTask.stream()
                .filter(taskVec2dPair -> !taskVec2dPair.getValue().equals(value1))
                .map(taskVec2dPair -> {
                    Pair<Task, Pair<Double, Double>> ret = new Pair<>(taskVec2dPair.getKey(), new Pair<>(Cosine.similarity(taskVec2dPair.getValue(), value1),value1.distanceSq(taskVec2dPair.getValue())));
                    return ret;
                })

                .filter(taskPairPair -> {
                    return 0.85 <= taskPairPair.getValue().getKey() && taskPairPair.getValue().getKey() <= 1.00;
                })
                .map(taskPairPair -> {
                    Pair<Task, Double> ret = new Pair<>(taskPairPair.getKey(), 1 / taskPairPair.getValue().getKey() * taskPairPair.getValue().getValue() );
                    return ret;
                })
                .sorted(Comparator.comparing(Pair::getValue))
                .findFirst().ifPresent(taskDoublePair -> System.out.println(taskDoublePair));
                //.forEach(taskPairPair -> System.out.println(taskPairPair));

    }

    Set<Pair> findSmallestDistancesBetweenSubjectsAndTasks() {

        return subjects.stream()
                .map(subject -> {
                    //System.out.print(subject.id + " --> ");
                    Optional<Task> min = tasks.stream()
                            .min((o1, o2) -> {
                                Point centerS = subject.getCenter();
                                Point centerO1 = o1.getCenter();
                                Point centerO2 = o2.getCenter();

                                double distO1 = Math.sqrt(Math.pow(centerO1.getX() - centerS.getX(), 2) + Math.pow(centerO1.getY() - centerS.getY(), 2));
                                double distO2 = Math.sqrt(Math.pow(centerO2.getX() - centerS.getX(), 2) + Math.pow(centerO2.getY() - centerS.getY(), 2));

                                return (int) (distO1 - distO2);

                            });
                    return new Pair(subject, min.get());
                }).collect(Collectors.toSet());

    }

    Pair findSmallestDistance() {
        Set<Pair> smallestDistancesBetweenSubjectsAndTasks = findSmallestDistancesBetweenSubjectsAndTasks();
        return smallestDistancesBetweenSubjectsAndTasks.stream().min((o1, o2) -> {
            double distanceO1 = Card.getDistance((Card) o1.getKey(), (Card) o1.getValue());
            double distanceO2 = Card.getDistance((Card) o2.getKey(), (Card) o2.getValue());
            return (int) (distanceO1 - distanceO2);
        }).get();

    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public Set<Transfer> getTransfers() {
        return transfers;
    }

    public Set<Pair<Subject, Set<Pair<Task, Vec2d>>>> getVectorSummary() {
        return vectorSummary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
