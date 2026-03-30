/*
 * Copyright 2026 David Navarre &lt;David.Navarre at irit.fr&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.utc.miage.shares;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class PortfolioTest {
    
    /**
     * Tests the constructor of the Portfolio class to ensure it creates an instance without throwing exceptions.
     */
    @Test
    void testConstructor() {
        assertDoesNotThrow(Portfolio::new);
    }

    /**
     * Tests the addActionQuantity method of the Portfolio class to ensure it correctly adds a specified quantity of an action to the portfolio and updates the quantity accordingly.
     */
    @Test
    void testAddActionQuantity() {
        Portfolio portfolio = new Portfolio();
        Action action = ActionSimpleTest.getDefaultActionSimple();
        portfolio.addActionQuantity(action, 1);
        assertEquals(1, portfolio.getActionQuantity(action));
    }

    /**
     * Tests the addActionQuantity method of the Portfolio class to ensure it throws an IllegalArgumentException when a negative quantity is provided.
     */
    @Test
    void testAddActionQuantityNegativeQuantity(){
        Portfolio portfolio = new Portfolio();
        Action action = ActionSimpleTest.getDefaultActionSimple();
        assertThrows(IllegalArgumentException.class, () -> portfolio.addActionQuantity(action, 0));
    }

    /**
     * Tests the addActionQuantity method of the Portfolio class to ensure it throws an IllegalArgumentException when a null action is provided.
     */
    @Test
    void testAddActionQuantityNullAction(){
        Portfolio portfolio = new Portfolio();
        assertThrows(IllegalArgumentException.class, () -> portfolio.addActionQuantity(null, 1));
    }

    @Test
    void testRemoveActionQuantity() {
        Portfolio portfolio = new Portfolio();
        Action action = ActionSimpleTest.getDefaultActionSimple();
        portfolio.addActionQuantity(action, 2);
        portfolio.removeActionQuantity(action, 1);
        assertEquals(1, portfolio.getActionQuantity(action));
    }

    @Test
    void testRemoveActionQuantityNegativeQuantity() {
        Portfolio portfolio = new Portfolio();
        Action action = ActionSimpleTest.getDefaultActionSimple();
        assertThrows(IllegalArgumentException.class, () -> portfolio.removeActionQuantity(action, 0));
    }

    @Test 
    void testRemoveActionQuantityNullAction() {
        Portfolio portfolio = new Portfolio();
        assertThrows(IllegalArgumentException.class, () -> portfolio.removeActionQuantity(null, 1));
    }

    @Test
    void testRemoveActionQuantityNotEnough() {
        Portfolio portfolio = new Portfolio();
        Action action = ActionSimpleTest.getDefaultActionSimple();
        portfolio.addActionQuantity(action, 1);
        assertThrows(IllegalArgumentException.class, () -> portfolio.removeActionQuantity(action, 2));
    }
    /**
     * Tests the getActions method of the Portfolio class to ensure it returns a correct map of all actions in the portfolio with their quantities.
     */
    @Test
    void testGetActions(){
        Portfolio portfolio = new Portfolio();
        Action action1 = ActionSimpleTest.getDefaultActionSimple();
        Action action2 = new ActionSimple("Action2");
        portfolio.addActionQuantity(action1, 1);
        portfolio.addActionQuantity(action2, 2);
        Map<Action, Integer> expectedActions = new HashMap<>();
        expectedActions.put(action1, 1);
        expectedActions.put(action2, 2);
        assertEquals(expectedActions, portfolio.getActions());
    }
}
