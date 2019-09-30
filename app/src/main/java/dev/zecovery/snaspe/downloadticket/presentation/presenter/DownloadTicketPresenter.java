package dev.zecovery.snaspe.downloadticket.presentation.presenter;

import com.appy.android.appycore.domain.usecase.UseCaseObserver;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.ticket.domain.model.Ticket;
import dev.zecovery.commons.ticket.domain.usecase.GetTicketListUseCase;
import dev.zecovery.commons.visitor.domain.model.Visitor;
import dev.zecovery.snaspe.downloadticket.presentation.contract.DownloadTicketContract;

import static dev.zecovery.commons.App.DELETE;
import static dev.zecovery.commons.App.NEW;
import static dev.zecovery.commons.App.TICKET;
import static dev.zecovery.commons.App.TICKET_ID;
import static dev.zecovery.commons.App.TICKET_PAYMENT_TIMESTAMP;
import static dev.zecovery.commons.App.TICKET_VISITOR;
import static dev.zecovery.commons.App.VISITOR_BIRTHDAY;
import static dev.zecovery.commons.App.VISITOR_DISABILITY;
import static dev.zecovery.commons.App.VISITOR_EMAIL;
import static dev.zecovery.commons.App.VISITOR_GENDER;
import static dev.zecovery.commons.App.VISITOR_ID;
import static dev.zecovery.commons.App.VISITOR_ID_TYPE;
import static dev.zecovery.commons.App.VISITOR_LAST_NAME;
import static dev.zecovery.commons.App.VISITOR_NAME;
import static dev.zecovery.commons.App.VISITOR_NATIONALITY;
import static dev.zecovery.commons.App.VISITOR_PHONE_NUMBER;
import static dev.zecovery.commons.App.VISITOR_REGION;
import static dev.zecovery.commons.App.VISITOR_SURNAME;

public class DownloadTicketPresenter implements DownloadTicketContract.Presenter {

    private static final String TAG = DownloadTicketPresenter.class.getCanonicalName();
    private DownloadTicketContract.View view;
    private GetTicketListUseCase getTicketListUseCase;

    @Inject
    public DownloadTicketPresenter(GetTicketListUseCase getTicketListUseCase) {
        this.getTicketListUseCase = getTicketListUseCase;
    }

    @Override
    public void initialize(DownloadTicketContract.View view) {
        this.view = view;
    }

    @Override
    public void downloadTickets(String apiKey, int parkId) {
        try {
            getTicketListUseCase.setData(apiKey, parkId).execute(new UseCaseObserver<JsonObject>() {
                @Override
                public void onNext(JsonObject jsonObject) {
                    super.onNext(jsonObject);

                    List<Ticket> ticketList = new ArrayList<>();
                    List<Visitor> visitorList = new ArrayList<>();

                    // Get list of tickets to delete
                    String list = jsonObject.get(DELETE).toString().replace("\"", "");
                    String[] ticketsToDelete = list.split(",");
                    List<String> toDelete = new ArrayList<>(Arrays.asList(ticketsToDelete));

                    // Get list of tickets
                    JsonElement element = jsonObject.get(NEW);
                    JsonArray jsonArray = element.getAsJsonArray();

                    for (JsonElement elementTicket : jsonArray) {
                        JsonObject j = elementTicket.getAsJsonObject();
                        JsonObject jsonTicket = j.get(TICKET).getAsJsonObject();

                        Ticket ticket = new Ticket();

                        if (!jsonTicket.get(TICKET_ID).isJsonNull()) {
                            ticket.ticketId = Integer.valueOf(jsonTicket.get(TICKET_ID).toString());
                        } else {
                            ticket.ticketId = 0;
                        }
                        if (!jsonTicket.get(TICKET_PAYMENT_TIMESTAMP).isJsonNull()) {
                            ticket.ticketPaymentTimestamp = Integer.valueOf(jsonTicket.get(TICKET_PAYMENT_TIMESTAMP).toString());
                        } else {
                            ticket.ticketPaymentTimestamp = 0;
                        }
                        Visitor visitor = new Visitor();

                        if (!jsonTicket.get(TICKET_VISITOR).isJsonNull()) {
                            JsonObject jsonVisitor = jsonTicket.get(TICKET_VISITOR).getAsJsonObject();
                            if (jsonVisitor.get(VISITOR_ID) != null) {
                                visitor.visitorId = jsonVisitor.get(VISITOR_ID).toString();
                            } else {
                                visitor.visitorId = "";
                            }

                            if (!jsonVisitor.get(VISITOR_ID_TYPE).isJsonNull()) {
                                visitor.visitorIdType = Integer.valueOf(jsonVisitor.get(VISITOR_ID_TYPE).toString());
                            } else {
                                visitor.visitorIdType = 0;
                            }

                            if (!jsonVisitor.get(VISITOR_NAME).isJsonNull()) {
                                visitor.visitorName = jsonVisitor.get(VISITOR_NAME).toString();
                            } else {
                                visitor.visitorName = "";
                            }

                            if (!jsonVisitor.get(VISITOR_LAST_NAME).isJsonNull()) {
                                visitor.visitorLastName = jsonVisitor.get(VISITOR_LAST_NAME).toString();
                            } else {
                                visitor.visitorLastName = "";
                            }

                            if (!jsonVisitor.get(VISITOR_SURNAME).isJsonNull()) {
                                visitor.visitorSurName = jsonVisitor.get(VISITOR_SURNAME).toString();
                            } else {
                                visitor.visitorSurName = "";
                            }

                            if (!jsonVisitor.get(VISITOR_GENDER).isJsonNull()) {
                                visitor.visitorGender = jsonVisitor.get(VISITOR_GENDER).toString();
                            } else {
                                visitor.visitorGender = "";
                            }
                            if (!jsonVisitor.get(VISITOR_BIRTHDAY).isJsonNull()) {
                                visitor.visitorBirthDate = Integer.valueOf(jsonVisitor.get(VISITOR_BIRTHDAY).toString());
                            } else {
                                visitor.visitorBirthDate = 0;
                            }

                            if (!jsonVisitor.get(VISITOR_EMAIL).isJsonNull()) {
                                visitor.visitorEmail = jsonVisitor.get(VISITOR_EMAIL).toString();
                            } else {
                                visitor.visitorEmail = "";
                            }

                            if (!jsonVisitor.get(VISITOR_PHONE_NUMBER).isJsonNull()) {
                                visitor.visitorPhoneNumber = jsonVisitor.get(VISITOR_PHONE_NUMBER).toString();
                            } else {
                                visitor.visitorPhoneNumber = "";
                            }

                            if (!jsonVisitor.get(VISITOR_DISABILITY).isJsonNull()) {
                                visitor.visitorDisability = Integer.valueOf(jsonVisitor.get(VISITOR_DISABILITY).toString());
                            } else {
                                visitor.visitorDisability = 0;
                            }

                            if (!jsonVisitor.get(VISITOR_NATIONALITY).isJsonNull()) {
                                visitor.visitorNationality = jsonVisitor.get(VISITOR_NATIONALITY).toString();
                            } else {
                                visitor.visitorNationality = "";
                            }

                            if (!jsonVisitor.get(VISITOR_REGION).isJsonNull()) {
                                visitor.visitorRegion = jsonVisitor.get(VISITOR_REGION).toString();
                            } else {
                                visitor.visitorRegion = "";
                            }

                            visitorList.add(visitor);
                        }

                        ticket.ticketVisitor = visitor;

                        ticketList.add(ticket);
                    }

                    // Save the list of visitors + ticketId
                    view.listOfVisitors(visitorList);
                    // Send list of tickets for work to view
                    view.onTicketsDownloaded(ticketList);
                    // Send list of tickets to delete to view
                    view.ticketListToDelete(toDelete);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                }
            });
        } catch (Exception e) {
            view.onTicketsDownloadedFailure(e.getMessage());
        }
    }
}
