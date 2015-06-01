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
    private ArrayList options = new ArrayList();
   
    
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
        this.options = new ArrayList<Option>();
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
        ResultSet rsChoix;
        String sql = "SELECT * from questionnaire where id=" + id;
        ResultSet rs = ordre.executeQuery(sql);
        if (rs.next()) {
            Statement stmt = cnx.createStatement();
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
                // recupere les choix de la question
                Option option;
                sql = "SELECT * FROM option WHERE idquestion=" + question.getId();
                rsChoix = stmt.executeQuery(sql);
                while (rsChoix.next()) {
                    option = new Option(rsChoix.getInt("id"),
                            question.getId(),
                            rsChoix.getString("libelle"), 
                            rsChoix.getBoolean("correct"));
                    question.getOptions().add(option);
                    
                }
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
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.libelle);
        hash = 83 * hash + Objects.hashCode(this.questions);
        hash = 83 * hash + Objects.hashCode(this.options);
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
        if (!Objects.equals(this.options, other.options)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "id=" + id + " " + libelle + " -> " + questions.toString();
    }
    
}
