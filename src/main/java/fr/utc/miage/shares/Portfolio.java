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

import java.util.HashMap;
import java.util.Map;

/**
 * This class embeds the common behavior of any Portfolio object.
 *
 * @author EtienneSalauze;
 */
public class Portfolio {
    
    private Map<Action, Integer> actions;

    /**
     * Builds a Portfolio object with a specified map of actions.
     */
    public Portfolio() {
        this.actions = new HashMap<>();
    }

    /**
     * Returns the quantity of a specified action in the portfolio.
     * 
     * @param action the action for which to get the quantity
     * @return the quantity of the specified action in the portfolio
     */
    public int getActionQuantity(Action action) {
        return this.actions.getOrDefault(action, 0);
    }

    /** 
    * Adds a specified quantity of an action to the portfolio.
    * @param action the action to add (must not be null)
    * @param quantity the quantity to add (must be positive)
    */
    public void addActionQuantity(Action action, Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (action == null) {
            throw new IllegalArgumentException("Action cannot be null");
        }
        this.actions.put(action, this.actions.getOrDefault(action, 0) + quantity);
    }

    /**
     * Return all actions in the portfolio.
     * @return a map of all actions in the portfolio with their quantities
     */
    public Map<Action, Integer> getActions() {
        return new HashMap<>(this.actions);
    }
}
