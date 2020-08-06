import java.util.Arrays;

public class BruteCollinearPoints {

    private int nSeg;
    private Point[] p;
    private LineSegment[] lSeg;

    public BruteCollinearPoints(Point[] points){

        //p = Arrays.copyOfRange(points, 0, points.length);
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

        // find out the number of segments
        nSeg = 0;
        if (p.length < 4){
            nSeg = 0;
        }
        for (int i=0; i<p.length; i++){
            for (int j=i+1; j<p.length; j++){
                for (int k=j+1;k<p.length;k++){
                    for (int l=k+1;l<p.length;l++){
                        double s1 = p[i].slopeTo(p[j]);
                        double s2 = p[i].slopeTo(p[k]);
                        double s3 = p[i].slopeTo(p[l]);
                        if (s1 == s2 && s2 == s3 && s1 == s3) {

                            if (nSeg >= lSeg.length){
                                LineSegment[] temp = new LineSegment[lSeg.length * 2 + 1];
                                for (int ind=0;ind<lSeg.length;ind++){
                                    temp[ind] = lSeg[ind];
                                }
                                lSeg = temp;
                            }

                            Point[] temp = new Point[4];
                            temp[0] = p[i];
                            temp[1] = p[j];
                            temp[2] = p[k];
                            temp[3] = p[l];
                            Arrays.sort(temp);

                            lSeg[nSeg] = new LineSegment(temp[0], temp[3]);
                            nSeg += 1;
                        }
                    }
                }
            }
        }

        //


    }    // finds all line segments containing 4 points
    public int numberOfSegments(){
        return nSeg;
    }        // the number of line segments
    public LineSegment[] segments(){
        return Arrays.copyOfRange(lSeg, 0, nSeg);
    }                // the line segments


}
