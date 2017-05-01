package datacenterbizapiexternal.wechat.domain;

public enum AccessTokenUrlEnum {
	// 新昌大佛寺—微信服务号
	XCDFS_ACCESS_TOKEN("https://api.weixin.qq.com/cgi-bin/token",
			"client_credential", "grant_type=%s&appid=%s&secret=%s",
			"wx6b69ea31a051918c", "46a4b426425d5e5534fa6a2dd3379607"),
	// 新昌旅游—微信订阅号
	XCLY_ACCESS_TOKEN("https://api.weixin.qq.com/cgi-bin/token",
			"client_credential", "grant_type=%s&appid=%s&secret=%s",
			"wx90d0350d71a56586", "91fa6beae59f2d4a570907cbe4772001");

	private String uri;
	private String grant_type;
	private String paramsFormatter;
	private String appID;
	private String appSecret;

	public static String urlFormatter = "%s?%s";

	private AccessTokenUrlEnum(String uri, String grant_type,
			String paramsFormatter, String appID, String appSecret) {
		this.uri = uri;
		this.grant_type = grant_type;
		this.paramsFormatter = paramsFormatter;
		this.appID = appID;
		this.appSecret = appSecret;
	}

	public String getUri() {
		return uri;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public String getParamsFormatter() {
		return paramsFormatter;
	}

	public String getAppID() {
		return appID;
	}

	public String getAppSecret() {
		return appSecret;
	}

}
