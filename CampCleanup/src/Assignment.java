
public class Assignment {
 private int lb,hb;

public Assignment() {
    super();
}

public Assignment(int i, int j) {
    lb=i;
    hb=j;
}

public int getLb() {
    return lb;
}

public void setLb(int lb) {
    this.lb = lb;
}

public int getHb() {
    return hb;
}

public void setHb(int hb) {
    this.hb = hb;
}
 
public boolean contains (int l, int h) {
    return (l>=lb&&h<=hb);
}
 
public boolean overlaps (int l,int h){
    return ((h<=hb&&h>=lb)||(l<=hb&&l>=lb));
}
    
}
