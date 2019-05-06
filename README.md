# ROMSIM

The system connects the service providers (e.g. RADET, ENEL, public administrations, etc.) with the citizens.

The system informs the citizens of stoppages, taxes, fees, road blocks, etc.

Topics represent adress information:

- example for Bucuresti:
/Bucuresti/\<district\>/\<street\>/\<number\>/\<block\>/\<entrance\</\<floor\>/<apartment> (for block addresses)
/Bucuresti/\<district\>/\<street\>/\<number\> (for houses addresses)

- example for other county:
/Galati/<city\|commune\|village\>/\<street\>/\<number\>/\<block\>/\<entrance\>/\<floor\>/\<apartment\> (for block addresses)
/Galati/\<city\|commune\|village\>/\<street\>/\<number\> (for houses addresses)

Publishers send messages to an address and subcribers receive messages. For example if a publisher sends a message to /Bucuresti/4/Unirii, all subscribers to that street receive the message.

A JMS2SMS Gateway subscribes to root topic / and sends sms messages to all registered users.

<p data-height="265" data-theme-id="0" data-slug-hash="zmQLXo" data-default-tab="html,result" data-user="titupetrea" data-pen-title="romsim" class="codepen">See the Pen <a href="https://codepen.io/titupetrea/pen/zmQLXo/">romsim</a> by Titu (<a href="https://codepen.io/titupetrea">@titupetrea</a>) on <a href="https://codepen.io">CodePen</a>.</p>

