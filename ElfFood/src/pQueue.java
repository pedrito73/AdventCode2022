import java.util.Random;

public class pQueue<T> {

    private final int MAXSIZE=100;
    private int c;
    private Node<T> valores[];
    private int max;

    @SuppressWarnings("unchecked")
    public pQueue(){
        valores=new Node[MAXSIZE];
        max=MAXSIZE;
        c=0;
    }

    public T getHead() {
        T retorno=first();
        valores[1]=valores[c];
        c--;
        hundir(1);
        if (c<max/4) reduceArray();
        return retorno;

    }

    public boolean insert(T t,int p) {
        return insert(new Node<T>(t,p));

    }

    private boolean insert(Node<T> node) {
        if (++c==max) duplicateArray();
        valores[c]=node;
        flotar(c);
        return true;
    }

    public boolean isEmpty() {
        return c==0;

    }

    public void flotar(int a) {
        while(a>1&&(valores[a/2].getP()<valores[a].getP())){
            swap(a,a/2);
            a=a/2;

        }
    }

    public void hundir(int a) {
        int hd,hi;
        int p;

        do{
            hi=2*a;
            hd=2*a+1;
            p=a;

            if(hd<=c&&valores[hd].getP()>valores[a].getP()) a=hd;
            if(hi<=c&&valores[hi].getP()>valores[a].getP()) a=hi;
            swap(p,a);
        } while(p!=a);
    }

    public T first() {
        return (T) valores[1].getT();}

    @SuppressWarnings("unchecked")
    private void duplicateArray(){
        Node<T> copy[]=new Node[max*2];
        for(int i=1;i<max;i++){
            copy[i]=valores[i];

        }
        max=max*2;
        valores=copy;

    }

    @SuppressWarnings("unchecked")
    private void reduceArray(){
        Node<T> copy[]=new Node[max/2+1];
        max=max/2+1;
        for(int i=1;i<max;i++){
            copy[i]=valores[i];

        }
        valores=copy;
    }

    private void swap(int p1,int p2) {
        Node<T> n=valores[p1];
        valores[p1]=valores[p2];
        valores[p2]=n;
    }

    public int size() {
        return c;
    }

    public static void main (String[] args){

        pQueue<String> q=new pQueue<String>();
        Random aleatorio=new Random();
        System.out.println("size:"+q.size());
        for(int i=0;i<1000;i++){
            int a=aleatorio.nextInt(100);
            int b=aleatorio.nextInt(200);
            q.insert(String.valueOf(a),b);
            System.out.println(i+":"+a+","+b);
        }
        System.out.println("size:"+q.size());
        for(int i=0;i<1000;i++){
            System.out.println(i+":"+q.getHead());
        }
        System.out.println("size:"+q.size());
    }    



    @SuppressWarnings("hiding")
    private class Node<T>{
        private T t;
        private int p;
        public Node(T t,int p) {
            this.t=t;
            this.p=p;
        }

        public T getT() {
            return t;
        }

        public int getP() {
            return p;
        }

    }
}