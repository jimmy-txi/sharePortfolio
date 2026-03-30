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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;

class InvestorTest {
    
    private final Investor investor = new Investor();
    private final Action action = new ActionSimple("Action1");  

    /**
     * Tests the constructor of the Investor class to ensure it creates an instance without throwing exceptions.
     */
    @Test
    void testConstructor() {
        assertDoesNotThrow(Investor::new);
        assertNotNull(investor);
    }

    /**
     * Tests the buy method of the Investor class to ensure it throws an IllegalArgumentException when a negative quantity is provided.
     */
    @Test
    void testBuyNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () -> investor.buy(action, -1));
    }

    @Test
    void testBuy(){
        assertDoesNotThrow(() -> investor.buy(action, 1));
    }

    @Test
    void testBuyNullAction(){
        assertThrows(IllegalArgumentException.class, () -> investor.buy(null, 1));
    }
}
