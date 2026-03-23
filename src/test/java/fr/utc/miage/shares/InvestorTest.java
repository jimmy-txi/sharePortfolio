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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class InvestorTest {
    
    @Test
    public void testConstructor() {
        Portfolio portfolio = new Portfolio(new HashMap<>());
        Investor investor = new Investor(portfolio);
        assertDoesNotThrow(() -> new Investor(portfolio));
        assertNotNull(investor);
    }

    @Test
    public void testBuy() {
        Portfolio portfolio = new Portfolio(new HashMap<>());
        Investor investor = new Investor(portfolio);
        Action action = new ActionSimple("Action1");
        investor.buy(action, 10);
        assertEquals(investor.getPortfolio().getActions().get(action), 10);
    }

    @Test
    public void testBuyNegativeQuantity() {
        Portfolio portfolio = new Portfolio(new HashMap<>());
        Investor investor = new Investor(portfolio);
        Action action = new ActionSimple("Action1");
        assertThrows(IllegalArgumentException.class, () -> investor.buy(action, -5));
    }


}
