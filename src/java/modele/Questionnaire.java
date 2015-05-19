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
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author fcd
 */
//@Entity
//@Table(name = "questionnaire")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Questionnaire.findAll", query = "SELECT q FROM Questionnaire q"),
//    @NamedQuery(name = "Questionnaire.findById", query = "SELECT q FROM Questionnaire q WHERE q.id = :id"),
//    @NamedQuery(name = "Questionnaire.findByLibelle", query = "SELECT q FROM Questionnaire q WHERE q.libelle = :libelle")})
public class Questionnaire implements Serializable {

    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "id")
    private Integer id;
//    @Basic(optional = false)
//    @Column(name = "libelle")
    private String libelle;
//    @OneToMany(mappedBy = "idQuestionnaire")
    private ArrayList questions = new ArrayList();

    public Questionnaire() {
        this(null, null);
    }

    public Questionnaire(Integer id) {
        this(id, null);
    }

    public Questionnaire(Integer id, String libelle) {
        this.id = id;
        this.libelle = libelle;
        this.questions = new ArrayList<Question>();
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

    public static Questionnaire getById(int id) throws SQLException {
        Questionnaire result = null;
        Connection cnx = Database.getConnection();
        Statement ordre = cnx.createStatement();
        String sql = "SELECT * from questionnaire where id=" + id;
        ResultSet rs = ordre.executeQuery(sql);
        if (rs.next()) {
            System.out.println("trouve");
            result = new Questionnaire(
                    rs.getInt("id"),
                    rs.getString("libelle")
            );
            // ajouter les questions
            sql = "SELECT * FROM question where id_questionnaire=" + id;
            rs = ordre.executeQuery(sql);
            Question question;
            while (rs.next()) {
                question = new Question(rs.getInt("id"), rs.getString("question"));
                result.questions.add(question);
            }
        } else {
            System.out.println("pas trouve");
        }
        cnx.close();
        return result;
    }

    @XmlTransient
    public Collection<Question> getQuestionCollection() {
        return questions;
    }

    public void setQuestionCollection(ArrayList<Question> questionCollection) {
        this.questions = questionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Questionnaire other = (Questionnaire) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        if (!Objects.equals(this.questions, other.questions)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id=" + id + " " + libelle + " -> " + questions.toString();
    }

}
