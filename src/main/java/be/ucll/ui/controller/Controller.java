package be.ucll.ui.controller;


import be.ucll.DomainException;
import be.ucll.domain.db.DierDB;
import be.ucll.domain.model.Dier;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final DierDB dierDB = new DierDB();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination;
        String command = request.getParameter("command");

        switch (command) {
            case "voegToeForm":
                destination = getVoegToeForm(request, response);
                break;
            case "overzicht":
                destination = getOverzicht(request, response);
                break;
            case "voegToe":
                destination = getVoegToe(request, response);
                break;
            default:
                destination = getHome(request, response);
                break;
        }

        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }

    private String getOverzicht(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("alleDieren", dierDB.getDieren());
        return "overzicht.jsp";
    }

    private String getHome(HttpServletRequest request, HttpServletResponse response) {
        return "index.jsp";
    }

    private String getVoegToeForm(HttpServletRequest request, HttpServletResponse response) {
        return "voegToe.jsp";
    }

    private String getVoegToe(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<String>();

        Dier dier = new Dier();
        setNaam(dier, request, errors);
        setSoort(dier, request, errors);
        setVoedsel(dier, request, errors);

        if (errors.size() == 0) {
            try {
                dierDB.voegToe(dier);
                return getOverzicht(request, response);
            } catch (DomainException exc) {
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "voegToe.jsp";
    }

    private void setNaam(Dier dier, HttpServletRequest request, ArrayList<String> errors) {
        String naam = request.getParameter("naam");
        boolean naamHasErrors = false;
        try {
            request.setAttribute("naamPreviousValue", naam);
            dier.setNaam(naam);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            naamHasErrors = true;
        } finally {
            request.setAttribute("naamHasErrors", naamHasErrors);
        }
    }

    private void setSoort(Dier dier, HttpServletRequest request, ArrayList<String> errors) {
        String soort = request.getParameter("soort");
        boolean soortHasErrors = false;
        try {
            request.setAttribute("soortPreviousValue", soort);
            dier.setSoort(soort);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            soortHasErrors = true;
        } finally {
            request.setAttribute("soortHasErrors", soortHasErrors);
        }
    }

    private void setVoedsel(Dier dier, HttpServletRequest request, ArrayList<String> errors) {
        String voedsel = request.getParameter("voedsel");
        boolean voedselHasErrors = false;
        try {
            request.setAttribute("voedselPreviousValue", voedsel);
            dier.setVoedsel(Integer.parseInt(voedsel));
        } catch (NumberFormatException exc) {
            errors.add("Vul een nummer in voor voedsel.");
            voedselHasErrors = true;
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            voedselHasErrors = true;
        } finally {
            request.setAttribute("voedselHasErrors", voedselHasErrors);
        }
    }

}