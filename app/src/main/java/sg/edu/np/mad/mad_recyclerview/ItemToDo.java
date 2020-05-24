package sg.edu.np.mad.mad_recyclerview;

public class ItemToDo {
    private String myToDo;
    private boolean mycheckbox;

    public String getMyToDo() {
        return myToDo;
    }

    public void setMyToDo(String myToDo) {
        this.myToDo = myToDo;
    }

    public boolean isMycheckbox() {
        return mycheckbox;
    }

    public void setMycheckbox(boolean mycheckbox) {
        this.mycheckbox = mycheckbox;
    }



    public ItemToDo(String toDoItem, boolean checkbox){
        myToDo = toDoItem;
        mycheckbox = checkbox;

    }
}
