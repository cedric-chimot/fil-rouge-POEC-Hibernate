import fr.cch.entity.*;
import fr.cch.service.*;

public class DataCreation {

    public static void newData() {

        UsersService usersService = new UsersService();
        usersService.save(new Users("Chimot", "Cedric", "01/01/01/01/01",
                "cedric02@hotmail.fr", "ced02830", "ced02830", UserRole.CANDIDAT));

        FormationsService formationsService = new FormationsService();
        formationsService.save(new Formations("Java JEE", 3500,
                "Formation au langage Java et au framework Spring"));

        CentreFormationService centreFormationService = new CentreFormationService();
        centreFormationService.save(new CentreFormation("IbCegos",
                "Gare Lille Flandres 59000 Lille"));

        SessionFormationService sessionFormationService = new SessionFormationService();
        sessionFormationService.save("03-01-2024", "02-02-2024",
                "en_attente", 1L, 1L);

        FormateursService formateursService = new FormateursService();
        formateursService.save(new Formateurs("Toto",
                "2 ans d'expérience en JAVA JEE",4.5));

        ParticipationService participationService = new ParticipationService();
        participationService.save(1L,1L,1L, 5, 4,
                true, 5, 4, 4, 4);

        SousThemesService sousThemesService = new SousThemesService();
        sousThemesService.save(new SousThemes("Java JEE"));
        sousThemesService.save( new SousThemes("C#"));

        DomaineService domaineService = new DomaineService();
        domaineService.save(new Domaine("Informatique"));

        ThemesService themesService = new ThemesService();
        themesService.save("Langage de développement", 1L);
        themesService.save("Langage de développement", 2L);

        DomaineThemesService domaineThemesService = new DomaineThemesService();
        domaineThemesService.save(1L, 1L);
        domaineThemesService.save(1L, 2L);

        FormationSousThemesService formationSousThemesService = new FormationSousThemesService();
        formationSousThemesService.save(1L, 1L);

    }

}
