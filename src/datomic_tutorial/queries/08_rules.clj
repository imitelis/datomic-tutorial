(ns datomic-tutorial.queries.08-rules)

;; We have had to write the following three lines of repetitive query code:
;; [?p :person/name ?name]
;; [?m :movie/cast ?p]
;; [?m :movie/title ?title]

;; Let's create a rule for the three lines above:
;; [(actor-movie ?name ?title)
;; [?p :person/name ?name]
;; [?m :movie/cast ?p]
;; [?m :movie/title ?title]]

;; The first vector is called the head of the rule where the first symbol 
;; is the name of the rule. The rest of the rule is called the body. 

;; The query to find cast members of some movie, for which we previously had to write:
;; [:find ?name
;;  :where
;;  [?p :person/name ?name]
;;  [?m :movie/cast ?p]
;;  [?m :movie/title "The Terminator"]]

;; Now becomes:
;; [:find ?name 
;;  :in $ %
;;  (actor-movie ?name "The Terminator")]

;; The % symbol in the :in clause represent the rules. 
;; You can write any number of rules,
;; [[(rule-a ?a ?b)
;;   ...]
;;  [(rule-b ?a ?b)
;;  ...]
;; ...]

;; Rules can also be used as another tool to write logical OR queries, 
;; as the same rule name can be used several times:
;; [[(associated-with ?person ?movie)
;;  [?movie :movie/cast ?person]]
;;  [(associated-with ?person ?movie)
;;  [?movie :movie/director ?person]]]

;; Using this rule, we can find both directors and cast members very easily:
;; [:find ?name
;; :in $ %
;; :where
;; [?m :movie/title "Predator"]
;; (associated-with ?p ?m)
;; [?p :person/name ?name]]