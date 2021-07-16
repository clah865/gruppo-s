package type;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class AdvObjectContainer extends AdvObject {

    private List<AdvObject> list = new ArrayList<>();

    public AdvObjectContainer(int id) {
        super(id);
    }

    public AdvObjectContainer(int id, String name) {
        super(id, name);
    }

    public AdvObjectContainer(int id, String name, String description) {
        super(id, name, description);
    }

    public AdvObjectContainer(int id, String name, String description, Set<String> alias) {
        super(id, name, description, alias);
    }

    public List<AdvObject> getList() {
        return list;
    }

    public void setList(List<AdvObject> list) {
        this.list = list;
    }

    public void add(AdvObject o) {
        list.add(o);
    }

    public void remove(AdvObject o) {
        list.remove(o);
    }

    public Object getObjects() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
