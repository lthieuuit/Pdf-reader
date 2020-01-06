package com.pnhphamhieu.ebookreader;

public class Files {
    public static final int GROUP_NEW = 0;
    public static final int GROUP_FAVORITE = 1;


    private int id;
    private String name;
    private int groupId;

    public Files(int id, String name, int groupId) {
        this.id = id;
        this.name = name;
        this.groupId = groupId;
    }

    public Files() {
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

}
