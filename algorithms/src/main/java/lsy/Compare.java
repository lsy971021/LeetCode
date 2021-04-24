package lsy;

public class Compare implements Comparable<Compare> {
    public int age;

    public Compare(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Compare o) {
        return (this.getAge() - o.getAge()) >= 0 ? 1 : -1;
    }

    public class test {
        public void main() {
            Compare compare1 = new Compare(10);
            Compare compare2 = new Compare(20);
            int i = compare1.compareTo(compare2);
            if (i >= 0) {
                System.out.println("compare1较大");
            }
        }
    }
}
