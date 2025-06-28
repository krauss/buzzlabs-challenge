# Buzzlabs Challenges

## Challenge 1

Create a counter with three buttons (Increment, Decrement and Reset) that does NOT save its state.

* Setup

1. Change directory:
```shell
$> cd buzzlabs-challenge
```

2. Run the `src/buzzlabs_challenge_1/core.cljs` file:
```shell
$> clj -M -m cljs.main -c buzzlabs-challenge-1.core -r
```


## Challenge 2

Create a counter with the same three buttons (Increment, Decrement and Reset) that saves its state in memory.

### Server setup

1. Change directory:
```shell
$> cd buzzlabs-challenge
```

2. Run the Clojure REPL:
```shell
$> clj -M
```

3. Import the server namespace::
```clojure
user=> (require '[buzzlabs-challenge-2.server :as srv])
```

4. Execute the `main` function:
```clojure
user=> (srv/main)
```

5. Open your browser on `http://localhost:8080`

### App setup

1. Change directory:
```shell
$> cd buzzlabs-challenge
```

2. Run the `src/buzzlabs_challenge_2/core.cljs` file:
```shell
$> clj -M -m cljs.main -c buzzlabs-challenge-2.core -r
```

## Challenge 3

Create a counter with the same three buttons (Increment, Decrement and Reset) that saves its state in a Datomic database.

### Database setup

Before start the steps below, make sure you have a Datomic database running on port **4334**. Instructions on how to set it up, can be found on this [link](https://docs.datomic.com/setup/pro-setup.html).

1. Change directory:
```shell
$> cd buzzlabs-challenge
```

2. Run the Clojure REPL:
```shell
$> clj -M
```

3. Import the **database** namespace::
```clojure
user=> (require '[buzzlabs-challenge-3.database])
```

4. If it all goes right, you should see a message been printed out: **DATABASE SETUP FINISHED!**


### Server setup

1. Change directory:
```shell
$> cd buzzlabs-challenge
```

2. Run the Clojure REPL:
```shell
$> clj -M
```

3. Import the **server** namespace::
```clojure
user=> (require '[buzzlabs-challenge-3.server :as srv])
```

4. Execute the `main` function:
```clojure
user=> (srv/main)
```

5. Open your browser on `http://localhost:8080`

### App setup

1. Change directory:
```shell
$> cd buzzlabs-challenge
```

2. Run the `src/buzzlabs_challenge_3/core.cljs` file:
```shell
$> clj -M -m cljs.main -c buzzlabs-challenge-3.core -r