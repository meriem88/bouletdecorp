@aleatoire
Feature: Tester la rubrique aléatoire
  Je souhaite tester la rubrique aléatoire

  @aleatoire_ok
  Scenario: Tester la rubrique aléatoire
    Given Je me connecte à l application bouletCorp
    When Je clique sur le bouton Aléatoire
    Then Je vérifie la page a changé
    And Je vérifie que les widjets facebook twiter et tumblr sont affichés
      | 
