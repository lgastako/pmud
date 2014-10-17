# pmud

Philosophy mud.  A lite MUD over Neo4j.

## Introduction

Users can create concepts.

    john> create concept One
    john> create concept Two

Users can hold concepts.

    dave> examine John's concepts
    One
    Two

Users can clone concepts.

    john> clone John's One

Any user can give any concept they hold to any other user for that user to hold
at any time.

    john> give One to Dave

* The purpose of this distinction from cloning is to allow for creative
collaborations e.g. where only one person can edit a concept or relation at a
time or as atoms, batons or some other type of token in a game, etc.

Concepts have properties.  Properties have names and values.

    john> show properties of One
    No properties of One have been defined yet.

A users that holds an concept may change the concept.

    > rename One to Unit
    One is now known as Unit.

Users can create (one-way or two-way) relations (edges) between any concepts.

    > Unit is greater than Nil

Users can hold relations.

Users can clone relations.

Any user can give any relation they hold to any other user for that user to hold at any time.

A users that holds an relation may change the relation.

Relations have properties.


+ Rooms (nodes)
+ Exits (edges)



## Versions

Every time you change a concept or a relation it creates a new version of that
concept or relation.  When you create a relation, the system stores the version
that the relation was pointing to.  You can specify whether the relation should
dynamically update or not, etc. todo fill this part in




