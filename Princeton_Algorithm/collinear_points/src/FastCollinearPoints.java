import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {

    private int nSeg;
    private LineSegment[] lSeg;
    private Point[] p;

    public FastCollinearPoints(Point[] points){

        // checking
        if (points == null){
            throw new IllegalArgumentException();
        }
        p = points.clone();
        for(int ind=0;ind<p.length;ind++){
            if (p[ind] == null) throw new IllegalArgumentException();
        }

        Arrays.sort(p);
        for(int ind=0;ind<p.length - 1;ind++) {
            if (p[ind].compareTo(p[ind + 1]) == 0) throw new IllegalArgumentException();

        }

        lSeg = new LineSegment[0];
        nSeg = 0;

        if (p.length <= 3) nSeg = 0;
        else{
            for(int ind=0;ind<p.length-3; ind++){
                // copy the rest
                Point[] subarray = new Point[p.length];
                for (int i=0;i<subarray.length;i++){
                    subarray[i] = p[i];
                }

                // sort by slope
                Arrays.sort(subarray, p[ind].slopeOrder());

                // check
                int i = 1;
                int found = 0;
                int count = 1;
                while (i<subarray.length){

                    if(subarray[i].slopeTo(p[ind]) == subarray[i-1].slopeTo(p[ind])) {
                        count += 1;
                        i ++;
                        if (count>=3) found = 1;
                    }

                    else{
                        if (found == 1) {
                            if (nSeg >= lSeg.length) resize();
                            Point[] temp = new Point[count + 1];
                            for (int tempInd=0;tempInd<count;tempInd++){
                                temp[tempInd] = subarray[i - tempInd - 1];
                            }
                            temp[count] = p[ind];
                            Arrays.sort(temp);
                            if (temp[0].compareTo(p[ind])==0){
                                lSeg[nSeg] = new LineSegment(temp[0], temp[count]);
                                nSeg ++;
                            }


//                            int check = 0;
//                            Point p1 = temp[0];
//                            Point p2 = temp[count];
//
//                            for (int indSeg=0;indSeg<nSeg;indSeg++){
//                                String[] tempS = lSeg[indSeg].toString().split(" -> ");
//                                String[] tempP3 = tempS[0].split(", ");
//                                String[] tempP4 = tempS[1].split(", ");
//                                Point p3 = new Point(Integer.parseInt(tempP3[0].replace("(", "")), Integer.parseInt(tempP3[1].replace(")", "" )));
//                                Point p4 = new Point(Integer.parseInt(tempP4[0].replace("(", "")), Integer.parseInt(tempP4[1].replace(")", "" )));
//                                double slope1 = p1.slopeTo(p2);
//                                double slope2 = p1.slopeTo(p3);
//                                double slope3 = p1.slopeTo(p4);
//                                if ((slope1 == slope2) && (slope1 == slope3)){
//                                    check = 1;
//                                    break;
//                                }
//                            }

//                            if (check ==0) {
//                                lSeg[nSeg] = new LineSegment(temp[0], temp[count]);
//                                nSeg ++;
//                            }

                        }
                        count = 1;
                        found = 0;
                        i ++;
                    }
                }
                if (found == 1) {
                    if (nSeg >= lSeg.length) resize();
                    Point[] temp = new Point[count + 1];
                    for (int tempInd=0;tempInd<count;tempInd++){
                        temp[tempInd] = subarray[i - tempInd - 1];
                    }
                    temp[count] = p[ind];
                    Arrays.sort(temp);
                    if (temp[0].compareTo(p[ind])==0){
                        lSeg[nSeg] = new LineSegment(temp[0], temp[count]);
                        nSeg ++;
                    }
//                    int check = 0;
//                    Point p1 = temp[0];
//                    Point p2 = temp[count];
//
//                    for (int indSeg=0;indSeg<nSeg;indSeg++){
//                        String[] tempS = lSeg[indSeg].toString().split(" -> ");
//                        String[] tempP3 = tempS[0].split(", ");
//                        String[] tempP4 = tempS[1].split(", ");
//                        Point p3 = new Point(Integer.parseInt(tempP3[0].replace("(", "")), Integer.parseInt(tempP3[1].replace(")", "" )));
//                        Point p4 = new Point(Integer.parseInt(tempP4[0].replace("(", "")), Integer.parseInt(tempP4[1].replace(")", "" )));
//                        double slope1 = p1.slopeTo(p2);
//                        double slope2 = p1.slopeTo(p3);
//                        double slope3 = p1.slopeTo(p4);
//                        if ((slope1 == slope2) && (slope1 == slope3)){
//                            check = 1;
//                            break;
//                        }
//                    }
//
//                    if (check ==0)  {
//                        lSeg[nSeg] = new LineSegment(temp[0], temp[count]);
//                        nSeg ++;
//                    }

                }
            }


        }



    }     // finds all line segments containing 4 or more points


    public int numberOfSegments(){
        return nSeg;
    }       // the number of line segments

    public LineSegment[] segments() {
        return Arrays.copyOfRange(lSeg, 0, nSeg);
    }             // the line segments

    private void resize(){
        LineSegment[] temp = new LineSegment[2 * lSeg.length + 1];
        for (int ind=0;ind<lSeg.length;ind++){
                temp[ind] = lSeg[ind];
        }
        lSeg = temp;
    }
    public static void main(String[] args) {
        // read the n points from a file
        In in = new In("input9.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

    }
