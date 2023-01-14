
# Why Seek You

Le logiciel se présente sous la forme d’un client lourd que chaque utilisateur lance pour
se connecter au réseau.

Après s’être identifié (ou avoir créé son compte la première fois), l’utilisateur voit
apparaître sa liste d’amis. La première fois elle est vide, il peut alors cliquer sur un
bouton « Ajouter un ami ».

En saisissant l’identifiant de son ami (email, pseudo, numéro, ...), l’ami est
automatiquement ajouté à la liste. Dans une évolution future, on aimerait qu’une
demande d’ami soit envoyée à l’ami en question, et l’ajout ne serait effectif que si l’ami
valide.

L’ajout à la liste d’amis est réciproque ! Si un utilisateur A ajoute l’utilisateur B, alors B
ajoutera aussi A automatiquement.
L’utilisateur peut aussi supprimer un ami de sa liste (auquel cas l’action sera elle aussi
réciproque).

Un utilisateur peut engager une conversation avec un ami de sa liste. Les deux
utilisateurs peuvent échanger, leur discussion doit rester privée et n’être stockée sur
aucun serveur !

La seule exception tolérée est les messages dits en « poste restante ». Si un utilisateur A
souhaite envoyer un message à un utilisateur B mais que celui-ci n’est pas/plus en ligne,
le serveur gardera le message pour lui jusqu’au moment de sa reconnexion, et lui
délivrera à ce moment là. Le message conservé sur le serveur sera alors supprimé.
On veut aussi pouvoir effectuer des envois de fichiers entre deux utilisateurs. L’envoi de
fichiers ne sera pas possible en « poste restante », les deux utilisateurs doivent
obligatoirement être connectés en même temps.

## Support

L'API à été developpé par Winston et réutilisé pour des raisons pratiques afin de tester les fonctionnalités par la suite.

Les Interfaces sont disponibles mais il n'est pas possible de créer un utilisateur ou se connecter, et il est aussi impossible d'envoyer des messages.

Le P2P est fonctionnels en dehors de l'interface via Main.java

L'API ainsi que l'interface sont disponibles via MainInterface.java



## API Reference

#### getTopic(idUsers1, idUsers2)

```http
  GET /api/topic/{idUsers1}/getTopic/{idUsers2}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `idUsers1`      | `long` | **Required**. id of first user |
| `idUsers2`      | `long` | **Required**. id of second user |
 
#### setTopic(idUsers1, idUsers2)

```http
  PUT /topic/{idUsers1}/setTopic/{idUsers2}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `idUsers1`      | `string` | **Required**. Id of the first user |
| `idUsers2`      | `string` | **Required**. Id of the second user |


#### setMessageEnAttente

```http
    POST /topic/{idUsers1}/setMessageEnAttente/{idUsers2}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `idUsers1`      | `string` | **Required**. Id of the first user |
| `idUsers2`      | `string` | **Required**. Id of the second user |
| `message`      | `string` | **Required**. Message to set as pending |



#### setPath

```http
      POST /topic/{idUsers1}/setPath/{idUsers2}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `idUsers1`      | `string` | **Required**. Id of the first user |
| `idUsers2`      | `string` | **Required**. Id of the second user |

#### getTopic

```http
        GET /topic/{idUsers1}/getTopic/{idUsers2}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `idUsers1`      | `string` | **Required**. Id of the first user |
| `idUsers2`      | `string` | **Required**. Id of the second user |


#### Get topic id

```http
         GET /topic/{idUsers1}/getTopicId/{idUsers2}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `idUsers1`      | `string` | **Required**. Id of the first user |
| `idUsers2`      | `string` | **Required**. Id of the second user |

#### Get path

```http
   GET /topic/{idUsers1}/getPath/{idUsers2}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `idUsers1`      | `Long` | **Required**. Id of the first user |
| `idUsers2`      | `Long` | **Required**. Id of the second user |

#### Delete message en attente

```http
     DELETE /topic/{idUsers1}/deleteMessageEnAttente/{idUsers2}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `idUsers1`      | `Long` | **Required**. Id of the first user |
| `idUsers2`      | `Long` | **Required**. Id of the second user |

## Tech Stack

**Client:** Java, FXML, JavaFx

**Server:** Spring, java.io
