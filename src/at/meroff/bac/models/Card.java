package at.meroff.bac.models;

import com.sun.javafx.geom.Vec2d;

public class Card {
    /**
     * Card Id
     */
    public int id;

    /**
     * First point of the card rectangle
     */
    Point p1;

    /**
     * Second point of the card rectangle
     */
    Point p2;

    /**
     * Third point of the card rectangle
     */
    Point p3;

    /**
     * Fourth point of the card rectangle
     */
    Point p4;

    // TODO Enum CardType anlegen.
    /**
     * Type of card
     */
    public CardType cardType;

    /**
     * Create a card
     * @param id Card ID
     * @param x1 X coordinates first corner
     * @param y1 Y coordinates first corner
     * @param x2 X coordinates second corner
     * @param y2 Y coordinates second corner
     * @param x3 X coordinates third corner
     * @param y3 Y coordinates third corner
     * @param x4 X coordinates fourth corner
     * @param y4 Y coordinates fourth corner
     * @param cardType Card type
     */
    public Card(int id, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, CardType cardType) {
        this.id = id;
        this.p1 = new Point(x1, y1);
        this.p2 = new Point(x2, y2);
        this.p3 = new Point(x3, y3);
        this.p4 = new Point(x4, y4);
        this.cardType = cardType;
    }

    /**
     * Method returns the center of the card
     * @return center point
     */
    public Point getCenter() {
        Point pt01 = new Point((p1.x + p3.x)/2, (p1.y + p3.y) / 2);
        Point pt02 = new Point((p2.x + p4.x)/2, (p2.y + p4.y) / 2);
        return new Point((pt01.x + pt02.x)/2, (pt01.y + pt02.y) / 2);
    }

    /**
     * Method returns the center of the card as an array
     * @return array with x,y coordinates for the center point
     */
    public double[] getCenterPointAsArray() {
        double[] test = new double[2];
        test[0] = getCenter().x;
        test[1] = getCenter().y;

        return test;
    }

    /**
     * Method returns the card type
     * @return
     */
    public CardType getCardType() {
        return cardType;
    }

    public int getSmallestX() {
        int smallestX = p1.x;
        if (p2.x < smallestX) smallestX = p2.x;
        if (p3.x < smallestX) smallestX = p3.x;
        if (p4.x < smallestX) smallestX = p4.x;
        return smallestX;
    }

    public int getSmallestY() {
        int smallestY = p1.y;
        if (p2.y < smallestY) smallestY = p2.y;
        if (p3.y < smallestY) smallestY = p3.y;
        if (p4.y < smallestY) smallestY = p4.y;
        return smallestY;
    }

    public int getBiggestX() {
        int biggestX = p1.x;
        if (p2.x > biggestX) biggestX = p2.x;
        if (p3.x > biggestX) biggestX = p3.x;
        if (p4.x > biggestX) biggestX = p4.x;
        return biggestX;
    }

    public int getBiggestY() {
        int biggestY = p1.y;
        if (p2.y > biggestY) biggestY = p2.y;
        if (p3.y > biggestY) biggestY = p3.y;
        if (p4.y > biggestY) biggestY = p4.y;
        return biggestY;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardType=" + cardType +
                '}';
    }

    public static double getDistance(Card source, Card target) {
        double xDist = target.getCenter().x - source.getCenter().x;
        double yDist = target.getCenter().y - source.getCenter().y;
        return Math.sqrt(Math.pow(xDist,2) + Math.pow(yDist,2));
    }

    public static Vec2d getVector(Card source, Card target) {
        double xVec = target.getCenter().x - source.getCenter().y;
        double yVec = target.getCenter().y - source.getCenter().y;

        return new Vec2d(xVec, yVec);

    }
}
