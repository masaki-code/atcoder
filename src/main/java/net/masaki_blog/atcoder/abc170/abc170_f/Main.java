package net.masaki_blog.atcoder.abc170.abc170_f;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;

public class Main {

    public static void main(String... args) throws Exception {
        new Main().execute();
    }

    BufferedReader br;
    PrintWriter pw;

    Main() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
        this.pw = new PrintWriter(System.out);
    }

    void execute() throws Exception {
        Input input = new Input(br);
        Global.init(input);

        Point firstPoint = new Point();
        firstPoint.i = input.x1;
        firstPoint.j = input.y1;

        int endI = input.x2;
        int endJ = input.y2;

        Point point = firstPoint;
        while (true) {
            manager.comfirm(point);
            manager.newUnconfirmed(point);
            point = manager.getMinUnconfirmed();
            if (point == null
                    || (point.i == endI && point.j == endJ)) {
                break;
            }
        }

        int cost;
        if (point == null) {
            cost = -1;
        } else {
            cost = point.costInteger;
            cost += point.costDecimal > 0 ? 1 : 0;
        }
        pw.println(cost);
        pw.flush();
    }

    static Global global;
    static ConfirmedManager manager;

    private static class Lotus {
        static boolean[][] lotus;

        static boolean get(int i, int j) {
            return lotus[i - 1][j - 1];
        }
    }

    private static class Confirm {

        private final Point[][] confirmMap;
        private final PriorityQueue<Point> unconfirmedSet;

        Confirm(int iMax, int jMax) {
            this.confirmMap = new Point[iMax][jMax];
            this.unconfirmedSet = new PriorityQueue<>();
        }

        Point get(int i, int j) {
            return confirmMap[i - 1][j - 1];
        }

        void set(Point point) {
            set(point.i, point.j, point);
        }

        void set(int i, int j, Point point) {
            confirmMap[i - 1][j - 1] = point;
        }

        boolean isConfirmed(int i, int j) {
            Point point = get(i, j);
            if (point == null) {
                return false;
            }
            return point.confirmed;
        }

        public void setUnconfirmed(Point point) {
            point.confirmed = false;
            unconfirmedSet.add(point);
            set(point);
        }

        void setConfirmed(Point point) {
            point.confirmed = true;
            unconfirmedSet.remove(point);
            set(point);
        }

        Point getFirst() {
            return unconfirmedSet.peek();
        }
    }

    private static class ConfirmedManager {
        private final Confirm confirm;

        ConfirmedManager(int southMax, int eastMax) {
            confirm = new Confirm(southMax, eastMax);
        }

        void comfirm(Point point) {
            confirm.setConfirmed(point);
        }

        boolean isConfirmed(int i, int j) {
            if (!global.isInErea(i, j)) {
                return true;
            }
            return confirm.isConfirmed(i, j);
        }

        void newUnconfirmed(Point confirmed) {
            int i = confirmed.i;
            int j = confirmed.j;
            if (!isConfirmed(i + 1, j)) {
                setUnconfirmed(i + 1, j, confirmed.goSouth());
            }
            if (!isConfirmed(i - 1, j)) {
                setUnconfirmed(i - 1, j, confirmed.goNorth());
            }
            if (!isConfirmed(i, j + 1)) {
                setUnconfirmed(i, j + 1, confirmed.goEast());
            }
            if (!isConfirmed(i, j - 1)) {
                setUnconfirmed(i, j - 1, confirmed.goWest());
            }
        }

        void setUnconfirmed(int i, int j, Point point) {
            if (point == null) {
                return;
            }
            Point current = confirm.get(i, j);
            if (current == null) {
                confirm.setUnconfirmed(point);
            } else {
                if (point.compareTo(current) < 0) {
                    confirm.setUnconfirmed(point);
                }
                if (point.compareTo(current) == 0) {
                    current.setDirection(point.direction);
                }
            }
        }

        Point getMinUnconfirmed() {
            return confirm.getFirst();
        }
    }

    static class Global {
        private final int southMax;
        private final int eastMax;
        private final int swimLen;

        Global(Input input) {
            southMax = input.H;
            eastMax = input.W;
            swimLen = input.K;
        }

        static void init(Input input) {
            Main.global = new Global(input);
            Main.manager = new ConfirmedManager(global.southMax, global.eastMax);
        }

        boolean isInErea(int i, int j) {
            if (i <= 0 || global.southMax < i) {
                return false;
            }
            if (j <= 0 || global.eastMax < j) {
                return false;
            }
            return true;
        }
    }

    private static class Point implements Comparable<Point> {

        @Override
        public int compareTo(Point p) {
            if (p.costInteger == this.costInteger) {
                if (p.costDecimal == this.costDecimal) {
                    return 0;
                } else {
                    return p.costDecimal < this.costDecimal ? 1 : -1;
                }
            } else {
                return p.costInteger < this.costInteger ? 1 : -1;
            }
        }

        private static final char East = 'E';
        private static final char West = 'W';
        private static final char South = 'S';
        private static final char North = 'N';

        int i; // 北からi番目
        int j; // 西からj番目

        char direction;

        private boolean east, west, south, north;

        private int costInteger;
        private int costDecimal;

        private boolean confirmed;

        Point goEast() {
            return go(East);
        }

        Point goWest() {
            return go(West);
        }

        Point goSouth() {
            return go(South);
        }

        Point goNorth() {
            return go(North);
        }

        private Point go(char direction) {
            int i = this.newI(direction);
            int j = this.newJ(direction);
            if (!global.isInErea(i, j) || Lotus.get(i, j)) {
                return null;
            }
            return newPoint(i, j, direction);
        }

        private int newI(char direction) {
            if (direction == South) {
                return this.i + 1;
            }
            if (direction == North) {
                return this.i - 1;
            }
            return this.i;
        }

        private int newJ(char direction) {
            if (direction == East) {
                return this.j + 1;
            }
            if (direction == West) {
                return this.j - 1;
            }
            return this.j;
        }

        private Point newPoint(int newI, int newJ, char direction) {
            Point point = new Point();
            point.i = newI;
            point.j = newJ;
            point.setDirection(direction);

            if (global.swimLen == 1) {
                point.costInteger = costInteger + 1;
                point.costDecimal = 0;
                return point;
            }

            if (sameDirection(direction)) {
                if (costDecimal + 1 == global.swimLen) {
                    point.costInteger = costInteger + 1;
                    point.costDecimal = 0;
                } else {
                    point.costInteger = costInteger;
                    point.costDecimal = costDecimal + 1;
                }
            } else {
                point.costInteger = costInteger;
                point.costInteger += costDecimal > 0 ? 1 : 0;
                point.costDecimal = 1;
            }
            return point;
        }

        void setDirection(char direction) {
            this.direction = direction;

            if (direction == East) {
                this.east = true;
            }
            if (direction == West) {
                this.west = true;
            }
            if (direction == South) {
                this.south = true;
            }
            if (direction == North) {
                this.north = true;
            }
        }

        private boolean sameDirection(char direction) {
            if ((direction == East && east) ||
                    (direction == West && west) ||
                    (direction == South && south) ||
                    (direction == North && north)) {
                return true;
            }
            return false;
        }
    }

    private static class Input {

        final int H, W, K;

        final int x1, y1;
        final int x2, y2;

        Input(BufferedReader br) throws IOException {
            this.H = readInt(br);
            this.W = readInt(br);
            this.K = readInt(br);
            this.x1 = readInt(br);
            this.y1 = readInt(br);
            this.x2 = readInt(br);
            this.y2 = readInt(br);

            Lotus.lotus = new boolean[H][W];
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    Lotus.lotus[i][j] = readLotus(br);
                }
                br.read();
            }
        }

        boolean readLotus(BufferedReader br) throws IOException {
            return br.read() == '@';
        }

        int readInt(BufferedReader br) throws IOException {
            int a = 0;
            while (true) {
                int read = br.read();
                if (48 <= read && read <= 57) {
                    a = a * 10 + read - 48;
                } else {
                    return a;
                }
            }
        }
    }
}