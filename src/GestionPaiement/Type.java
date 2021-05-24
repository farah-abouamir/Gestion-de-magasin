package GestionPaiement;

public class Type {
    public long id;
    public String type;

    @Override
    public String toString() {
        return ""+type ;
    }
    public Type(long id, String type) {
        this.id = id;
        this.type = type;
    }
    public Type(String type) {
        this.type = type;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }



}
