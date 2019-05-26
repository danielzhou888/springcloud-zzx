import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: springcloud-zzx
 * @description:
 * @author: zhouzhixiang
 * @create: 2019-04-22 19:36
 */
public class Test {
    public static void main(String[] args) {
        User z1 = new User("z1", 23);
        User z2 = new User("z2", 25);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z1);

        System.out.println(atomicReference.compareAndSet(z1, z2) + "\t" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z1, z2) + "\t" + atomicReference.get().toString());

    }

    static class User {
        String name;
        int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
