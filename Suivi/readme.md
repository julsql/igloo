Ce fichier contient et contiendra des remarques de suivi sur votre
projet tant sur la modélisation que sur la programmation. Un nouveau
suivi est indiqué par une nouvelle section datée.

Certaines remarques demandent des actions de votre part, vous les
repérerez par une case à cocher.

- []  Action (à réaliser) 

Merci de nous indiquer que vous avez pris en compte la remarque en
cochant la case. N'hésitez pas à écrire dans ce fichier et à nous
exposer votre point de vue.

- [x] Action (réalisée)
    - RÉPONSE et éventuelles remarques de votre part, 
 
**Les messages standardisés avec leur explication sont dans le dépôt des exemples et plus particulièrement [ici](https://gitlabens.imtbs-tsp.eu/enseignants.csc4102/csc4102-exemples/-/blob/main/Suivi/messages_pour_le_suivi.md)**

---
# Suivi du lun. 20 févr. 2023 12:52:08
Denis Conan

Bon début !
Complétez les préconditions et adaptez les tables de décision (pour savoir si vous avez complètement compris les tables de décision : vos tables actuelles sont très/trop simples)

* [X] DIAGUC-04-Formulation-non-appropriée : détail, « Mise... » devrait plutôt être « Mettre... » + pb cohérence avec formulation dans les préconditions/postconditions

* [X] PREPOSTCOND-05-Précondition-incomplète
    * mettre à la corbeille une tâche : comment trouve-t-on la tâche ? avec un identifiant, non ? et l'identifiant doit être bien formé
    * mettre à la corbeille un développeur

* [X] PREPOSTCOND-01-Pré-post-condition-manquante : à faire = « ajouter une tâche » ; ce n'est pas le cas d'utilisation le plus difficile ;-)
 
* [X] tables de décision à mettre à jour après mise à jour des préconditions et postconditions

* [X] TABLEDECTV-03-Incohérence-pré-post-conditions : il faut essayer de mettre toutes les postconditions dans les tables de décision
 
---
# Suivi du mar. 07 mars 2023 09:46:03
Denis Conan
 
* Rappel : https://gitlabens.imtbs-tsp.eu/enseignants.csc4102/csc4102-exemples/-/blob/main/Suivi/messages_pour_le_suivi.md

* Je vous conseille de travailler sur les éléments à reprendre avant
  la prochaine séance pour pouvoir passer ensuite à la programmation
  sans avoir besoin de spécifier/modéliser (beaucoup).

* [X] GEN-01-Indiquer-remarque-prise-en-compte
Les remarques ont été prises en compte.

* [X] GEN-08-Manque-fichiers-images : les diagrammes

* [X] GIT-02-Git-pas-fichier-temporaire : retirez le répertoir `cachedir`
Ok c'est retiré

* je regarde à partir du diagramme de classes : au besoin, posez des
  questions explicitement sur les modifications des éléments
  précédents

* diagramme de classes : ok (pour la conception préliminaire)
    * ne tardez pas à faire les préconditions et postconditions car,
      par exemple, vous mettez des « intitulés » d'activité et de
      tâche => cohérence avec le vocabulaire dans les préconditions et
      postconditions

* [X] DIAGSEQ-21-Pb-cohérence-avec-diag-classes : quid de l'attribut
  description de la tâche dans « ajouter une tâche »
C'est ajouté

* [X] message « create » : il manque les arguments

* principe diag. de séquence compris

* [X] DIAGSEQ-21-Pb-cohérence-avec-diag-classes : dans « mettre à la
  corbeille une tâche », « nomActivité » ?
  
* [X] DIAGSEQ-02-Compréhension-étude-de-cas : attention ! ce n'est pas
  « supprimer... »

* [X] DIAGSEQ-06-Pb-syntaxe-message-affectation-attribut : erreur sur
  message « r5.corbeille = true » + tel qu'écrit, corbeille est un
  attribut => attribut qui n'est pas privé (c'est un problème)

* [X] DIAGSEQ-06-Pb-syntaxe-message-affectation-attribut : idem sur
  p.corbeille...

* [X] mauvais type de fragment pour le plus imbriqué + besoin de faire
  un appel sur chaque période de travail 
---
# Suivi du mar. 14 mars 2023 10:06:32
Denis Conan

Vous avez besoin de travailler entre les séances, par exemple pour
être prêts pour la programmation.

* [X] DIAGCLAS-32-Nom-rôle : activité au pluriel, etc.

* [X] DIAGCLA-30-Role-et-nom-association : des associations sans nom
  d'associations (entre Activité et Tâche, etc.)
  
* [X] DIAGCLAS-10-Erreur-multiplicité : toujours une période de travail
  associée à une tâche ?

* diag. de séquence « ajouter une tâche » : ok

* [X] pourquoi le message récursif mettreCorbeille(r5) sur r2 ?

* [X] « loop [toutes les périodes p ] » suffit : pas besoin d'une
  recherche de p avant

* [X] pourquoi le message récursif mettreCorbeille(p) sur r5 ?

* [X] DIAGMACHETATS-01-Diagramme-manquant

* [X] INV-01-Invariant-manquant

* [X] TABLEDECTU-01-Table-de-décision-manquante
 
---
# Suivi du ven. 17 mars 2023 13:50:26
Denis Conan

Bravo ! C'est pas mal du tout ;-)
Bon démarrage de la programmation. Des éléments seront à ajouter après
le cours de la séance 6.

* [x] DIAGCLAS-17-Nom-classe

* [X] comme vous laissez les associations bidirectionnelles, les
  attributs dansCorbeille sont des attributs dérivés, non ?
  * Non pertinent

* [X] Les agrégations et les compositions ayant une sémantique
  « ensemble contient éléments », il n'est pas nécessaire de mettre un
  nom d'association ; le nom d'association est nécessaire lorsque ce
  n'est ni une agrégation ni une composition
    * en trop sur l'agrégation et sur la composition
    * par exemple manque entre Développeur et PériodeDeTravail

* [X] DIAGCLAS-10-Erreur-multiplicité : de Période... vers Dév...

* diag. séquence mettre à la corbeille : ok

* diag. machine à états : ok

* [X] comme vous laissez les associations bidirectionnelles, les
  attributs dansCorbeille sont des attributs dérivés, non ?
  + RAFDIAGCLAS-05-Traduction-attribut-dérivé-manquante
  * Non pertinent

* [X] INV-03-Invariant-à-compléter : manque termes sur les attributs qui correspondent aux associations + les attributs dérivés (montrer la redondance d'information)

* fiche de la classe tâche : mettre les attributs autres que ceux
  traduisant des associations

* [X] TABLEDECTU-03-Incohérence-prototype : pas les mêmes arguments que le create dans le diagramme de séquence « ajouter une tâche »

* [X] pb pagination dernière table de décision

* [X] JAVA-13-Avertissement-compilation-code : facile à résoudre, mais y penser

* [X] invariant de Developpeur à compléter avec les nouveaux attributs

* [x] invariant de Activite à compléter

* [X] idem autres invariants

* [X] JAVA-06-Pb-traduction-association : classe PeriodeDeTravail

* [X] JAVA-04-Cohérence-avec-diagrammes-de-séquence :
  SuiPro::ajouterUneTache et
  SuiPro::mettreCorbeilleUneTache... activite.getTaches().get(intituleTache)
  c'est différent du diag. de séquence
    * c'est le diag. de séquence qui est le plus propre
------------------------------------
lun. 03 avril 2023 16:09:01
Denis Conan

----

Pour rappel, vous trouverez les explications sur les messages à l'adresse suivante :
https://gitlabens.imtbs-tsp.eu/enseignants.csc4102/csc4102-exemples/-/blob/main/Suivi/messages_pour_le_suivi.md

# Évaluation du logiciel livré à la fin du Sprint 1

Très bon travail! Les remarques suivantes vont encore vous aider à
affiner et consolider votre projet pour le Sprint 2. Bonne continuation.

## Modélisation du logiciel

### Spécification et préparation des tests de validation

#### Diagrammes de cas d'utilisation =  a

#### Préconditions et postconditions =  b+

Cas d'utilisation « ajouter une tâche » : 

* PREPOSTCOND-02-Compréhension-étude-de-cas,
  PREPOSTCOND-05-Précondition-incomplète : termes sur l'activité

Cas d'utilisation « mettre à la corbeille une tâche » : 

* PREPOSTCOND-02-Compréhension-étude-de-cas,
  PREPOSTCOND-05-Précondition-incomplète : termes sur l'activité

Cas d'utilisation « mettre à la corbeille un développeur » : ok

#### Tables de décision des tests de validation =  a

Cas d'utilisation « ajouter une tâche » : 

* [] TABLEDECTV-07-MAJ-précondition-postcondition

Cas d'utilisation « mettre à la corbeille une tâche » : 

* [] TABLEDECTV-07-MAJ-précondition-postcondition

Cas d'utilisation « mettre à la corbeille un développeur » : ok

### Conception préliminaire

#### Diagramme de classes =  a

* redondance entre nom d'associatin et agrégation : quand agrégation,
  pas besoin de nom d'association

#### Diagrammes de séquence (2 parmi) =  a

Cas d'utilisation « ajouter une tâche » : ok

Cas d'utilisation « mettre à la corbeille une tâche » : ok

Cas d'utilisation « mettre à la corbeille un développeur » : N/A

### Conception détaillée et préparation des tests unitaires

#### Raffinement du diagramme de classes =  a

#### Diagramme de machine à états et invariant =  a-

* [] INV-03-Invariant-à-compléter : attribut association vers Activité

#### Tables de décision de tests unitaires =  a

## Programmation

### Utilisation des outils de programmation

#### Module Maven et tests avec JUnit = a

### Programmation de la solution

#### Classes du diagramme de classes avec leurs attributs = a-

* [] JAVA-06-Pb-traduction-association : Activité vers la façade car
  association bidirectionnelle

* [] Tâche::equals et hashCode avec des attributs différents

#### Méthodes des cas d'utilisation de base = a+

Cas d'utilisation « ajouter une tâche » : ok

Cas d'utilisation « mettre à la corbeille une tâche » : ok

Cas d'utilisation « mettre à la corbeille un développeur » : ok

### Cohérence entre le code et le modèle

#### Cohérences du code avec le diagramme de classes = a-

#### Cohérences du code avec les diagrammes de séquence de base = a

### Programmation et exécution des tests de validation et unitaires

#### Tests de validation des cas d'utilisation = b+

Cas d'utilisation « ajouter une tâche » : 

* [] JAVATEST-05-Pb-nommage-méthode-de-test : ajouterUneTacheTest2Jeu1
  alors qu'il y a deux tests dans la méthode

Cas d'utilisation « mettre à la corbeille une tâche » : 

* [] JAVATEST-08-Test-validation-sans-accès-à-l-intérieur : pb sur
  suiPro.getActivites()

* [] JAVATEST-05-Test-manquant : avec périodes de travail

Cas d'utilisation « mettre à la corbeille un développeur » : 

* [] JAVATEST-05-Pb-nommage-méthode-de-test : mettreCorbeilleUneTache, etc.

* [] JAVATEST-08-Test-validation-sans-accès-à-l-intérieur : pb sur
  suiPro.getDeveloppeurs(), etc.

#### Tests unitaires de méthodes d'une classe = a


 
---
# Suivi du mar. 04 avril 2023 09:32:43

* spotbugs ok

* [] checkstyle à terminer

* [] Activite::toString : y mettre plus de chose



