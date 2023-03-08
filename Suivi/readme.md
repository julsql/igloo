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

* [] PREPOSTCOND-05-Précondition-incomplète
    * mettre à la corbeille une tâche : comment trouve-t-on la tâche ? avec un identifiant, non ? et l'identifiant doit être bien formé
    * mettre à la corbeille un développeur

* [] PREPOSTCOND-01-Pré-post-condition-manquante : à faire = « ajouter une tâche » ; ce n'est pas le cas d'utilisation le plus difficile ;-)
 
* [] tables de décision à mettre à jour après mise à jour des préconditions et postconditions

* [] TABLEDECTV-03-Incohérence-pré-post-conditions : il faut essayer de mettre toutes les postconditions dans les tables de décision
 
---
# Suivi du mar. 07 mars 2023 09:46:03
Denis Conan
 
* Rappel : https://gitlabens.imtbs-tsp.eu/enseignants.csc4102/csc4102-exemples/-/blob/main/Suivi/messages_pour_le_suivi.md

* Je vous conseille de travailler sur les éléments à reprendre avant
  la prochaine séance pour pouvoir passer ensuite à la programmation
  sans avoir besoin de spécifier/modéliser (beaucoup).

* [] GEN-01-Indiquer-remarque-prise-en-compte

* [] GEN-08-Manque-fichiers-images : les diagrammes

* [] GIT-02-Git-pas-fichier-temporaire : retirez le répertoir `cachedir`

* je regarde à partir du diagramme de classes : au besoin, posez des
  questions explicitement sur les modifications des éléments
  précédents

* diagramme de classes : ok (pour la conception préliminaire)
    * ne tardez pas à faire les préconditions et postconditions car,
      par exemple, vous mettez des « intitulés » d'activité et de
      tâche => cohérence avec le vocabulaire dans les préconditions et
      postconditions

* [] DIAGSEQ-21-Pb-cohérence-avec-diag-classes : quid de l'attribut
  description de la tâche dans « ajouter une tâche »

* [] message « create » : il manque les arguments

* principe diag. de séquence compris

* [] DIAGSEQ-21-Pb-cohérence-avec-diag-classes : dans « mettre à la
  corbeille une tâche », « nomActivité » ?
  
* [] DIAGSEQ-02-Compréhension-étude-de-cas : attention ! ce n'est pas
  « supprimer... »

* [] DIAGSEQ-06-Pb-syntaxe-message-affectation-attribut : erreur sur
  message « r5.corbeille = true » + tel qu'écrit, corbeille est un
  attribut => attribut qui n'est pas privé (c'est un problème)

* [] DIAGSEQ-06-Pb-syntaxe-message-affectation-attribut : idem sur
  p.corbeille...

* [] mauvais type de fragment pour le plus imbriqué + besoin de faire
  un appel sur chaque période de travail
  


