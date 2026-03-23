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

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class PortfolioTest {
    
    @Test
    public void testConstructor() {
        Map<Action, Integer> actions = new HashMap<>();
        Portfolio portfolio = new Portfolio(actions);
        assertDoesNotThrow(() -> new Portfolio(actions));
    }

    @Test
    public void testGetActions() {
        // Create a Portfolio object with a sample map of actions
        Map<Action, Integer> sampleActions = new HashMap<>();
        final Action action1 = new ActionSimple("Action1");
        final Action action2 = new ActionSimple("Action2");
        sampleActions.put(action1, 10);
        sampleActions.put(action2, 20);
        
        Portfolio portfolio = new Portfolio(sampleActions);
        
        // Test the getActions method
        Map<Action, Integer> retrievedActions = portfolio.getActions();
        
        // Assert that the retrieved actions match the sample actions
        assertEquals(sampleActions, retrievedActions);
    }

    @Test
    public void testSetActions() {
        // Create a Portfolio object with an initial map of actions
        Map<Action, Integer> initialActions = new HashMap<>();
        Action action1 = new ActionSimple("Action1");
        Action action2 = new ActionSimple("Action2");
        initialActions.put(action1, 10);
        initialActions.put(action2, 20);
        
        Portfolio portfolio = new Portfolio(initialActions);
        
        // Create a new map of actions to set
        Map<Action, Integer> newActions = new HashMap<>();
        Action action3 = new ActionSimple("Action3");
        Action action4 = new ActionSimple("Action4");
        newActions.put(action3, 30);
        newActions.put(action4, 40);
        
        // Set the new actions in the portfolio
        portfolio.setActions(newActions);
        
        // Assert that the portfolio's actions have been updated to the new actions
        assertEquals(newActions, portfolio.getActions());
    }

    @Test
    public void testSetActionsWithNull() {
        // Create a Portfolio object with an initial map of actions
        Map<Action, Integer> initialActions = new HashMap<>();
        Action action1 = new ActionSimple("Action1");
        Action action2 = new ActionSimple("Action2");
        initialActions.put(action1, 10);
        initialActions.put(action2, 20);
        
        Portfolio portfolio = new Portfolio(initialActions);
        
        // Set the actions to null
        portfolio.setActions(null);
        
        // Assert that the portfolio's actions have been updated to null
        assertEquals(null, portfolio.getActions());
    }
}
