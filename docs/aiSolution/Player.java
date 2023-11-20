// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;

// class Player {
//     private String name;
//     private Store store;

//     public Player(String name) {
//         this.name = name;
//         this.store = new Store(0);
//     }

//     public String getName() {
//         return name;
//     }

//     public Store getStore() {
//         return store;
//     }
// }

public class Player {
    private String name;
    private Store store;

    public Player(String name) {
        this.name = name;
        this.store = new Store(0);
    }

    public String getName() {
        return name;
    }

    public Store getStore() {
        return store;
    }
}
