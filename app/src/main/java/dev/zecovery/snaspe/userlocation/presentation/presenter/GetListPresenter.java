package dev.zecovery.snaspe.userlocation.presentation.presenter;

import com.appy.android.appycore.domain.usecase.UseCaseObserver;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.control.domain.model.Control;
import dev.zecovery.commons.park.domain.model.Park;
import dev.zecovery.commons.park.domain.usecase.GetParkListUseCase;
import dev.zecovery.commons.service.domain.model.Service;
import dev.zecovery.snaspe.BuildConfig;
import dev.zecovery.snaspe.userlocation.presentation.contract.GetListContract;

public class GetListPresenter implements GetListContract.Presenter {

    private static final String TAG = GetListPresenter.class.getCanonicalName();
    private GetListContract.View view;
    private GetParkListUseCase getParkListUseCase;

    @Inject
    public GetListPresenter(GetParkListUseCase getParkListUseCase) {
        this.getParkListUseCase = getParkListUseCase;
    }

    @Override
    public void initialize(GetListContract.View view) {
        this.view = view;
    }

    @Override
    public void getLocationList() {
        try {
            getParkListUseCase.setData(BuildConfig.URL_KEY).execute(new UseCaseObserver<List<Park>>() {
                @Override
                public void onNext(List<Park> parkList) {
                    super.onNext(parkList);
                    List<Service> serviceList = new ArrayList<>();
                    List<Control> controlList = new ArrayList<>();

                    for (Park park : parkList) {
                        for (JsonElement element : park.parkServiceList) {
                            JsonObject jsonService = element.getAsJsonObject();
                            Service service = new Service();
                            service.serviceId = Integer.valueOf(jsonService.get("serv_id").toString());
                            service.serviceName = jsonService.get("serv_nombre").toString().replace("\"", "");
                            service.serviceParkId = park.parkId;
                            service.serviceControlList = new JsonArray();
                            service.serviceControlList = jsonService.getAsJsonArray("serv_lugar_control");
                            for (JsonElement elementControl : service.serviceControlList) {
                                JsonObject jsonControl = elementControl.getAsJsonObject();
                                Control control = new Control();
                                control.controlParkId = park.parkId;
                                control.controlServiceId = service.serviceId;
                                control.controlName = jsonControl.get("cont_nombre").toString().replace("\"", "");
                                control.controlId = Integer.valueOf(jsonControl.get("cont_id").toString());
                                controlList.add(control);
                            }
                            serviceList.add(service);
                        }
                    }
                    view.onGetParkList(parkList);
                    view.onGetServiceList(serviceList);
                    view.onGetControlList(controlList);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.onGetListFailure(e.getMessage());
                }
            });
        } catch (Exception e) {
            view.onGetListFailure(e.getMessage());
        }
    }
}
