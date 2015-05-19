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
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fcd
 */
@Entity
@Table(name = "question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q"),
    @NamedQuery(name = "Question.findById", query = "SELECT q FROM Question q WHERE q.id = :id"),
    @NamedQuery(name = "Question.findByQuestion", query = "SELECT q FROM Question q WHERE q.question = :question")})
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "question")
    private String question;
    @OneToMany(mappedBy = "idQuestion")
    private Collection<Reponse> reponseCollection;
    @JoinColumn(name = "id_questionnaire", referencedColumnName = "id")
    @ManyToOne
    private Questionnaire idQuestionnaire;
    @JoinColumn(name = "id_rep", referencedColumnName = "id")
    @ManyToOne
    private Reponse idRep;

    public Question() {
    }

    public Question(Integer id) {
        this.id = id;
    }

    public Question(Integer id, String question) {
        this.id = id;
        this.question = question;
       
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
    @XmlTransient
    public Collection<Reponse> getReponseCollection() {
        return reponseCollection;
    }

    public void setReponseCollection(Collection<Reponse> reponseCollection) {
        this.reponseCollection = reponseCollection;
    }

    public Questionnaire getIdQuestionnaire() {
        return idQuestionnaire;
    }

    public void setIdQuestionnaire(Questionnaire idQuestionnaire) {
        this.idQuestionnaire = idQuestionnaire;
    }

    public Reponse getIdRep() {
        return idRep;
    }

    public void setIdRep(Reponse idRep) {
        this.idRep = idRep;
    }
    public static Question getById(int id) throws SQLException{
        Question result = null;
        Connection cnx = Database.getConnection();
        Statement ordre = cnx.createStatement();
        String sql = "SELECT * from question where id=" + id;
        ResultSet rs = ordre.executeQuery(sql);
        if (rs.next()) {
            result = new Question(
                    rs.getInt("id"),
                    rs.getString("question")
                    
                    
                    
                    
                    
            );
        }
        cnx.close();
        return result;
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
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id + " : " + question;
    }
    
}
