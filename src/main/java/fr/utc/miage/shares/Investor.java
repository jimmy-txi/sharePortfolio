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

/**
* This class embeds the common behavior of any Investor object.
*
* @author EtienneSalauze;
*/
public class Investor {
    
    private Portfolio portfolio;
    
    /**
    * Builds an Investor object with a specified portfolio.
    *
    */
    public Investor() {
        this.portfolio = new Portfolio();
    }

    /**
    *  Buys a specified quantity of a given action and updates the portfolio accordingly.
    *
    * @param a the action to buy
    * @param quantity the quantity to buy
    */
    public void buy(Action a, int quantity){
        if(quantity <= 0){
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }else{
            this.portfolio.addActionQuantity(a, quantity);
        }
    }
}
