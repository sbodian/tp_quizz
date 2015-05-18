/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Collection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fcd
 */
public class QuestionnaireIT {

    @Test
    public void testGetById() throws Exception {
        System.out.println("getById");
        Questionnaire expResult = new Questionnaire(4, "Quiz 4 sur les surnoms des sportifs");
        Question question = new Question(90, "Quel sportif était surnommé le Kid de Las Vegas ?");
        expResult.getQuestionCollection().add(question);
        question = new Question(91, "Quel basketteur était surnommé Le Facteur ?");
        expResult.getQuestionCollection().add(question);
        Questionnaire result = Questionnaire.getById(4);
        assertEquals(expResult, result);
    }

}
