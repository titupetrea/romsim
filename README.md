# ROMIS
ROmanian Mass Information System

The system connects the service providers (e.g. RADET, ENEL, public administrations, etc.) with the citizens.

The system informs the citizens of stoppages, taxes, fees, road blocks, etc.

Topics represent adress information:

- example for Bucharest:
/Bucharest/<district>/<street>/<number>/<entrance>/<block>/<floor>/<apartment> (for block addresses)
/Bucharest/<district>/<street>/<number> (for houses addresses)

- example for other county:
/Galati/<city|commune|village>/<street>/<number>/<entrance>/<block>/<floor>/<apartment> (for block addresses)
/Galati/<city|commune|village>/<street>/<number> (for houses addresses)

Publishers send messages to an address and subcribers receive messages. For example if a publisher sends a message to /Bucharest/1/Unirii, all subscribers to that street receive the message.
