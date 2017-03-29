package fr.lille.iut.appytroc;

/**
 * Created by thibault on 29/03/2017.
 */

public class Offer {

    private int id;
    private int id_user;
    private String titre;
    private String detail;
    private boolean active;

    public Offer(int id,int id_user,String titre,String detail){
        this.id=id;
        this.id_user=id_user;
        this.titre=titre;
        this.detail=detail;
        this.active=true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
