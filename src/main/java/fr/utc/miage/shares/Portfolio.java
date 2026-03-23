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

import java.util.Map;

/**
 * This class embeds the common behavior of any Portfolio object.
 *
 * @author EtienneSalauze;
 */
public class Portfolio {
    
    private Map<Action, Integer> actions;

    public Portfolio(Map<Action, Integer> actions) {
        this.actions = actions;
    }

    public Map<Action, Integer> getActions() {
        return actions;
    }

    public void setActions(Map<Action, Integer> actions) {
        this.actions = actions;
    }

}
