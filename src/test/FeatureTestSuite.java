package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  CdeAjouterLivraisonTest.class,
  CdeSupprimerLivraisonTest.class,
  CheminTest.class,
  ControleurTest.class,
  LivraisonTest.class,
  NoeudTest.class,
  PlageHoraireTest.class,
  TourneeTest.class,
  TronconTest.class,
  ZoneTest.class
})


/*
 * @author : Kevin
 */
public class FeatureTestSuite {
  // the class remains empty,
  // used only as a holder for the above annotations
}