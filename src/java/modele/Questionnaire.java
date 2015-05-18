/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import modele.Question;

/**
 *
 * @author fcd
 */
@Entity
@Table(name = "questionnaire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Questionnaire.findAll", query = "SELECT q FROM Questionnaire q"),
    @NamedQuery(name = "Questionnaire.findById", query = "SELECT q FROM Questionnaire q WHERE q.id = :id"),
    @NamedQuery(name = "Questionnaire.findByLibelle", query = "SELECT q FROM Questionnaire q WHERE q.libelle = :libelle")})
public class Questionnaire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "libelle")
    private String libelle;
    @OneToMany(mappedBy = "idQuestionnaire")
    private ArrayList al = new ArrayList();

    public Questionnaire() {
    }

    public Questionnaire(Integer id) {
        this.id = id;
    }

    public Questionnaire(Integer id, String libelle) {
        this.id = id;
        this.libelle = libelle;
        //this.questionCollection = questionCollection;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    
    public static Questionnaire getById(int id) throws SQLException{
        Questionnaire result = null;
        Connection cnx = Database.getConnection();
        Statement ordre = cnx.createStatement();
        String sql = "SELECT * from questionnaire where id=" + id;
        ResultSet rs = ordre.executeQuery(sql);
        if (rs.next()) {
            result = new Questionnaire(
                    rs.getInt("id"),
                    rs.getString("libelle")
                    
                    
                    
            );
        }
        cnx.close();
        return result;
    }

    @XmlTransient
    public Collection<Question> getQuestionCollection() {
        return al;
    }

    public void setQuestionCollection(ArrayList<Question> questionCollection) {
        this.al = questionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Questionnaire)) {
            return false;
        }
        Questionnaire other = (Questionnaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.Questionnaire[ id=" + id + " ]";
    }
    
}
