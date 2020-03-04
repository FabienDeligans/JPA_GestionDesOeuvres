/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "proprietaire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proprietaire.findAll", query = "SELECT p FROM Proprietaire p"),
    @NamedQuery(name = "Proprietaire.findByIdProprietaire", query = "SELECT p FROM Proprietaire p WHERE p.idProprietaire = :idProprietaire"),
    @NamedQuery(name = "Proprietaire.findByNomProprietaire", query = "SELECT p FROM Proprietaire p WHERE p.nomProprietaire = :nomProprietaire"),
    @NamedQuery(name = "Proprietaire.findByPrenomProprietaire", query = "SELECT p FROM Proprietaire p WHERE p.prenomProprietaire = :prenomProprietaire"),
    @NamedQuery(name = "Proprietaire.findByLogin", query = "SELECT p FROM Proprietaire p WHERE p.login = :login"),
    @NamedQuery(name = "Proprietaire.findByPwd", query = "SELECT p FROM Proprietaire p WHERE p.pwd = :pwd")})
public class Proprietaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proprietaire")
    private Integer idProprietaire;
    @Size(max = 50)
    @Column(name = "nom_proprietaire")
    private String nomProprietaire;
    @Size(max = 50)
    @Column(name = "prenom_proprietaire")
    private String prenomProprietaire;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "pwd")
    private String pwd;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proprietaire")
    private List<Oeuvre> oeuvreList;

    public Proprietaire() {
    }

    public Proprietaire(Integer idProprietaire) {
        this.idProprietaire = idProprietaire;
    }

    public Proprietaire(Integer idProprietaire, String login, String pwd) {
        this.idProprietaire = idProprietaire;
        this.login = login;
        this.pwd = pwd;
    }

    public Integer getIdProprietaire() {
        return idProprietaire;
    }

    public void setIdProprietaire(Integer idProprietaire) {
        this.idProprietaire = idProprietaire;
    }

    public String getNomProprietaire() {
        return nomProprietaire;
    }

    public void setNomProprietaire(String nomProprietaire) {
        this.nomProprietaire = nomProprietaire;
    }

    public String getPrenomProprietaire() {
        return prenomProprietaire;
    }

    public void setPrenomProprietaire(String prenomProprietaire) {
        this.prenomProprietaire = prenomProprietaire;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @XmlTransient
    public List<Oeuvre> getOeuvreList() {
        return oeuvreList;
    }

    public void setOeuvreList(List<Oeuvre> oeuvreList) {
        this.oeuvreList = oeuvreList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProprietaire != null ? idProprietaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proprietaire)) {
            return false;
        }
        Proprietaire other = (Proprietaire) object;
        if ((this.idProprietaire == null && other.idProprietaire != null) || (this.idProprietaire != null && !this.idProprietaire.equals(other.idProprietaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dal.Proprietaire[ idProprietaire=" + idProprietaire + " ]";
    }
    
}
