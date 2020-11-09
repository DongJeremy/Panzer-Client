package org.cloud.panzer.api;

import org.cloud.panzer.api.service.AreaService;
import org.cloud.panzer.api.service.ConnectionService;
import org.cloud.panzer.api.service.IndexService;
import org.cloud.panzer.api.service.LoginService;
import org.cloud.panzer.api.service.MemberService;

public interface ApiService extends IndexService, MemberService, AreaService, ConnectionService, LoginService {
}
