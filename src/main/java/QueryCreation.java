import fr.cch.service.*;

public class QueryCreation {

    public static void newQueries() {

        UsersService usersService = new UsersService();
        System.out.println(usersService.findUser(1L));

        FormationsService formationsService = new FormationsService();
        System.out.println(formationsService.findFormation(1L).getSessionsFormation());

        ThemesService themesService = new ThemesService();
        System.out.println(themesService.findThemes(1L).toString());

        CentreFormationService centreFormationService = new CentreFormationService();
        System.out.println(centreFormationService.findCentreFormation(1L).getSessionsFormation());

        ParticipationService participationService = new ParticipationService();
        System.out.println(participationService.findParticipation(1L, 1L, 1L));

        SessionFormationService sessionFormationService = new SessionFormationService();
        System.out.println(sessionFormationService.findSession(1L));

        DomaineThemesService domaineThemesService = new DomaineThemesService();
        System.out.println(domaineThemesService.findDomaineThemes(1L, 2L));

        FormationSousThemesService formationSousThemesService = new FormationSousThemesService();
        System.out.println(formationSousThemesService.findFormationSousThemes(1L, 1L));

    }

}
