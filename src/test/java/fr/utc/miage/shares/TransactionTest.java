package fr.utc.miage.shares;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Copyright 2025 David Navarre &lt;David.Navarre at irit.fr&gt;.
 *
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

class TransactionTest {

    private static final String FOO_SHARE1 = "Foo Share 1";
    private static final String FOO_SHARE2 = "Foo Share 2";
    private static final Jour jourTest = new Jour(2026,76);
    private static final float PRICE_TEST = 10.0f;

    @Test
    void getAction() {
        Action action = new ActionSimple(FOO_SHARE1);
        Transaction transaction = new Transaction(action, jourTest, PRICE_TEST);
        assertEquals(action, transaction.getAction());
    }

    @Test
    void setAction() {
        Action action = new ActionSimple(FOO_SHARE1);
        Transaction transaction = new Transaction(action, jourTest, PRICE_TEST);
        Action action2 = new ActionSimple(FOO_SHARE2);
        transaction.setAction(action2);
        assertEquals(action2, transaction.getAction());
    }

    @Test
    void getJour() {
        Action action = new ActionSimple(FOO_SHARE1);
        Transaction transaction = new Transaction(action, jourTest, PRICE_TEST);
        assertEquals(jourTest, transaction.getJour());
    }

    @Test
    void setJour() {
        Action action = new ActionSimple(FOO_SHARE1);
        Transaction transaction = new Transaction(action, jourTest, PRICE_TEST);
        Jour jour2 = new Jour(2026,77);
        transaction.setJour(jour2);
        assertEquals(jour2, transaction.getJour());
    }

    @Test
    void getPrice() {
        Action action = new ActionSimple(FOO_SHARE1);
        Transaction transaction = new Transaction(action, jourTest, PRICE_TEST);
        assertEquals(PRICE_TEST, transaction.getPrice());
    }

    @Test
    void setPrice() {
        Action action = new ActionSimple(FOO_SHARE1);
        Transaction transaction = new Transaction(action, jourTest, PRICE_TEST);
        float price2 = 11.0f;
        transaction.setPrice(price2);
        assertEquals(price2, transaction.getPrice());
    }
}