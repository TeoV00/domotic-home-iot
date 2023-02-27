package it.unibo.esiot.service.agents;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import it.unibo.esiot.service.model.HomeStateImpl;
import it.unibo.esiot.service.model.SensorData;

/*
 * Data Service as a vertx event-loop 
 */
public class HTTPServerAgent extends AbstractVerticle {

	private static final String PATH_HOME_STATE = "/api/data/home-state";
	private static final String PATH_SENSORS = "/api/data/sensors";
	private final int port;
	private final HomeStateImpl homeState;
	private final SensorData sensorData;

	public HTTPServerAgent(final int port, final HomeStateImpl garden, final SensorData sensorData) {
		this.port = port;
		this.homeState = garden;
		this.sensorData = sensorData;
	}

	@Override
	public void start() {		
		Router router = Router.router(vertx);
		router.route().handler(io.vertx.ext.web.handler.CorsHandler.create(".*.")
				.allowedMethod(io.vertx.core.http.HttpMethod.GET)
				.allowedMethod(io.vertx.core.http.HttpMethod.OPTIONS)
				//.allowedMethod(HttpMethod.PUT)
				.allowedHeader("Access-Control-Request-Method")
				.allowedHeader("Access-Control-Allow-Origin")
				.allowedHeader("Access-Control-Allow-Headers")
				.allowedHeader("Content-Type"));
		router.route().handler(BodyHandler.create());
		router.get(PATH_HOME_STATE).handler(this::handleGetHomeState);
		router.get(PATH_SENSORS).handler(this::handleGetSensors);
		vertx.createHttpServer()
				 .requestHandler(router)
				 .listen(port);
		log("Service ready.");
	}

	private void handleGetHomeState(final RoutingContext routingContext) {
		routingContext.response()
				.putHeader("content-type", "application/json")
				.end(this.homeState.getJson());
		log("ricevuta richiesta homestate");
	}

	private void handleGetSensors(final RoutingContext routingContext) {
		routingContext.response()
				.putHeader("content-type", "application/json")
				.end(this.sensorData.getJson(true));
		log(this.sensorData.getJson(true));
	}

	private void log(final String msg) {
		System.out.println("HTTP_SEVER_SERVICE: " + msg);
	}

}