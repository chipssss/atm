package entity;

/**
 * @author: chips
 * @date: 2020-06-06
 * @description:
 **/
public enum Operation {
    WITHDRAW("取款");
    String name;

    Operation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
