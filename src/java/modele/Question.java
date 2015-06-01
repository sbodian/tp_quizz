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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
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
    private Collection<Option> options;
    @JoinColumn(name = "id_questionnaire", referencedColumnName = "id")
    @ManyToOne
    private Questionnaire idQuestionnaire;
    @JoinColumn(name = "id_rep", referencedColumnName = "id")
    @ManyToOne
    private Reponse idRep;
    
   

    public Question() {
    }

    public Question(Integer id, String question) {
        this.id = id;
        this.question = question;
        options = new ArrayList<Option>();
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
    public Collection<Option> getOptions() {
        return options;
    }

    public void setOptions(Collection<Option> options) {
        this.options = options;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.question);
        hash = 37 * hash + Objects.hashCode(this.options);
        hash = 37 * hash + Objects.hashCode(this.idQuestionnaire);
        hash = 37 * hash + Objects.hashCode(this.idRep);
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
        final Question other = (Question) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.question, other.question)) {
            return false;
        }
        if (!Objects.equals(this.options, other.options)) {
            return false;
        }
        if (!Objects.equals(this.idQuestionnaire, other.idQuestionnaire)) {
            return false;
        }
        if (!Objects.equals(this.idRep, other.idRep)) {
            return false;
        }
        return true;
    }
    
   

    @Override
    public String toString() {
        return id + " : " + question;
    }

   
}
