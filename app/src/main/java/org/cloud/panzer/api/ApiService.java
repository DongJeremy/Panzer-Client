package org.cloud.panzer.api;

import org.cloud.panzer.api.service.AreaService;
import org.cloud.panzer.api.service.ConnectionService;
import org.cloud.panzer.api.service.GoodsService;
import org.cloud.panzer.api.service.IndexService;
import org.cloud.panzer.api.service.LoginService;
import org.cloud.panzer.api.service.MemberFavoritesService;
import org.cloud.panzer.api.service.MemberService;
import org.cloud.panzer.api.service.StoreService;
import org.cloud.panzer.api.service.VoucherService;

public interface ApiService extends IndexService, MemberService, AreaService, ConnectionService, LoginService,
        GoodsService, StoreService, VoucherService, MemberFavoritesService {
}