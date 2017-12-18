package at.meroff.bac;

import at.meroff.bac.models.*;
import com.sun.javafx.geom.Vec2d;
import javafx.util.Pair;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Field {
    Set<Card> subjects = new HashSet<>();
    Set<Card> tasks = new HashSet<>();
    Set<Card> transfers = new HashSet<>();
    String name;

    public Field() {

    }


    public Set<Pair<Card, Set<Pair<Card, Vec2d>>>> getVectorSummary() {

        return subjects.stream()
                .map(subject -> {
                    return new Pair<>(subject, tasks.stream()
                            .map(task -> new Pair<>(task, new Vec2d(task.getCenter().x - subject.getCenter().x, task.getCenter().y - subject.getCenter().y)))
                            .collect(Collectors.toSet()));
                }).collect(Collectors.toSet());
    }

    public Task getNearestTask(Subject subject) {
        Set<Pair> collect = tasks.stream()
                .map(task -> {
                    Vec2d vec2d = new Vec2d(task.getCenter().x - subject.getCenter().x, task.getCenter().y - subject.getCenter().y);
                    return new Pair(task, Math.sqrt(Math.pow(vec2d.x, 2) + Math.pow(vec2d.y, 2)));
                }).collect(Collectors.toSet());
        return (Task)collect.stream()
                .sorted(new Comparator<Pair>() {
                    @Override
                    public int compare(Pair o1, Pair o2) {
                        if ((double)o1.getValue() < (double)o2.getValue()) {
                            return -1;
                        } else if (o1.getValue().equals(o2.getValue())) {
                            return 0; // You can change this to make it then look at the
                            //words alphabetical order
                        } else {
                            return 1;
                        }
                    }
                })
                .findFirst().get().getKey();

    }

    void addCard(Card card) {
        if (card.cardType == CardType.SUBJECT) {
            subjects.add((Subject)card);
        } else if (card.cardType == CardType.TASK) {
            tasks.add((Task)card);
        } else {
            transfers.add((Transfer)card);
        }
    }

    public Set<Card> getSubjects() {
        return subjects;
    }

    public Set<Card> getTasks() {
        return tasks;
    }

    public Set<Card> getTransfers() {
        return transfers;
    }

    void getShortestDistanceBetweenBlueAndRed() {
        double smallestDist = Double.MAX_VALUE;
        for (Card subject : subjects) {
            OptionalDouble min = tasks
                    .stream()
                    .mapToDouble(card -> {
                        double pow = Math.pow(card.getCenter().x - subject.getCenter().x, 2);
                        double pow1 = Math.pow(card.getCenter().y - subject.getCenter().y, 2);
                        return Math.sqrt((pow + pow1));
                    })
                    .min();
            if (min.isPresent()) {
                System.out.println(subject.id + " " + min.getAsDouble());
            }
        }
    }

    Rectangle2D getRectangle(CardType cardType) {
        Set<Card> cards = new HashSet<>();

        if (cardType.equals(CardType.SUBJECT)) cards = subjects;
        if (cardType.equals(CardType.TASK)) cards = tasks;
        int smallestX = cards.stream().mapToInt(Card::getSmallestX).min().getAsInt();
        int smallestY = cards.stream().mapToInt(Card::getSmallestY).min().getAsInt();
        int biggestX = cards.stream().mapToInt(Card::getBiggestX).max().getAsInt();
        int biggestY = cards.stream().mapToInt(Card::getBiggestY).max().getAsInt();

        return new Rectangle2D.Float(smallestX,smallestY,biggestX-smallestX,biggestY-smallestY);
    }

    double getRectangleSize(CardType cardType) {
        Rectangle2D rectangle = getRectangle(cardType);
        return rectangle.getWidth() * rectangle.getHeight();
    }

    public double getOverlapPercentages(CardType source) {
        CardType target;

        if (source.equals(CardType.SUBJECT)) target = CardType.TASK; else target = CardType.SUBJECT;

        Rectangle2D sourceRectangle = getRectangle(source);
        Rectangle2D targetRectangle = getRectangle(target);

        return getIntersectingArea2(sourceRectangle, targetRectangle) / getRectangleSize(source) * 100;

    }

    double getIntersectingArea(Rectangle2D source, Rectangle2D target) {
        double x_overlap = Math.max(0, Math.min(source.getX() + source.getWidth(), target.getX() + target.getWidth()) - Math.max(source.getX(), target.getX()));
        double y_overlap = Math.max(0, Math.min(source.getY() + source.getHeight(), target.getY() + source.getHeight()) - Math.max(source.getY(), target.getY()));
        return x_overlap * y_overlap;
    }

    double getIntersectingArea2(Rectangle2D source, Rectangle2D target) {
        Rectangle r1 = new Rectangle((int)source.getX(), (int)source.getY(), (int)source.getWidth(), (int)source.getHeight());
        Rectangle r2 = new Rectangle((int)target.getX(), (int)target.getY(), (int)target.getWidth(), (int)target.getHeight());
        Rectangle intersection = r1.intersection(r2);
        double v = intersection.getWidth() * intersection.getHeight();
        return v;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    List<Vec2d> getAllVectors() {
        List<Vec2d> vList = new LinkedList<>();

        subjects.stream()
                .forEach(card -> {
                    tasks.stream()
                            .forEach(card1 -> {
                                Vec2d vec2d = new Vec2d(card1.getCenter().x - card.getCenter().x, card1.getCenter().y - card.getCenter().y);
                                vList.add(vec2d);
                            });
                });
        return vList;
    }

    List<Vec2d> getAllVectors(Card cardSearch) {
        List<Vec2d> vList = new LinkedList<>();

        subjects.stream()
                .filter(card -> card.equals(cardSearch))
                .forEach(card -> {
                    tasks.stream()
                            .forEach(card1 -> {
                                vList.add(new Vec2d(card1.getCenter().x - card.getCenter().x, card1.getCenter().y - card.getCenter().y));
                            });
                });
        return vList;
    }

    public Set<Pair> findAllVectorsForSubject(Card subject) {

        return tasks.stream()
                .map(task -> new Pair(new Vec2d(task.getCenter().x - subject.getCenter().x, task.getCenter().y - subject.getCenter().y), task))
                .collect(Collectors.toSet());

    }


    void getCardAssociation() {
        Set<Pair<Card, Set<Card>>> collect = subjects.stream()
                .map(subject -> {
                    Pair<Card, Set<Card>> pair;
                    Set<Card> assocTasks = new HashSet<>();

                    // get all vectors vor given subject


                    pair = new Pair<Card, Set<Card>>(subject, assocTasks);

                    return pair;

                }).collect(Collectors.toSet());
    }

    public Long getMainVector() {

        ArrayList<Card> cards = new ArrayList<>(tasks);

        List<Vec2d> allVectors = getAllVectors();

        Pair<Vec2d, Long> collect = allVectors.stream()
                //.peek(System.out::println)
                .map(vec2d -> {
                    long count = allVectors.stream()
                            .filter(vec2d1 -> !vec2d.equals(vec2d1))
                            .mapToDouble(vec2d1 -> Cosine.similarity(vec2d, vec2d1))
                            .filter(value -> 0.95 <= value && value <= 1.00)
                            //.peek(value -> System.out.println("\t" + vec2d + " - " + value))
                            .count();
                    return new Pair<>(vec2d, count);
                })
                //.peek(vec2dLongPair -> System.out.println(vec2dLongPair.getKey() + " --- " + ((double)vec2dLongPair.getValue() / allVectors.size())))
                .max(Comparator.comparingLong(Pair::getValue)).get();

        //System.out.println(collect.getKey() + " --- " + ((double)collect.getValue() / allVectors.size()));
        return collect.getValue();

    }

    public Long getMainVector(Card searchCard) {

        ArrayList<Card> cards = new ArrayList<>(tasks);

        List<Vec2d> allVectors = getAllVectors(searchCard);

        Pair<Vec2d, Long> collect = allVectors.stream()
                //.peek(System.out::println)
                .map(vec2d -> {
                    long count = allVectors.stream()
                            .filter(vec2d1 -> !vec2d.equals(vec2d1))
                            .mapToDouble(vec2d1 -> Cosine.similarity(vec2d, vec2d1))
                            .filter(value -> 0.95 <= value && value <= 1.00)
                            //.peek(value -> System.out.println("\t" + vec2d + " - " + value))
                            .count();
                    return new Pair<>(vec2d, count);
                })
                //.peek(vec2dLongPair -> System.out.println(vec2dLongPair.getKey() + " --- " + ((double)vec2dLongPair.getValue() / allVectors.size())))
                .max(Comparator.comparingLong(Pair::getValue)).get();



        System.out.println(collect.getKey() + " --- " + ((double)collect.getValue() / allVectors.size()));

        return collect.getValue();

    }

    public Set<Pair> getMainVector2(Card subject) {

        List<Vec2d> allVectors = getAllVectors(subject);

        return allVectors.stream()
                .map(basisVector -> {
                    Set<Vec2d> collect = allVectors.stream()
                            .filter(targetVector -> !basisVector.equals(targetVector))
                            .filter(targetVector -> {
                                double similarity = Cosine.similarity(basisVector, targetVector);
                                return 0.95 <= similarity && similarity <= 1.00;
                            }).collect(Collectors.toSet());
                    return new Pair(basisVector, collect);
                }).collect(Collectors.toSet());


    }

    public String getRBasic() {

        String ret = "plot(NA, xlim=c(-1200,1200), ylim=c(-1200,1200), xlab=\"X\", ylab=\"Y\")\n";

        ArrayList<Card> subjectsArray = new ArrayList<>(subjects);
        ArrayList<Card> tasksArray = new ArrayList<>(tasks);

        int countVectors = subjectsArray.size() * tasksArray.size();

        ret += "vecs <- data.frame(\n" +
                "                   x0=c(";

        for (int i = 0; i < countVectors; i++) {
            ret += "0,";
        }

        ret += "),y0=c(";

        for (int i = 0; i < countVectors; i++) {
            ret += "0,";
        }

        ret += "), \n" +
                "                   x1=c(";

        return ret;

    }

}
