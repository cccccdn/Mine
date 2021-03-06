/*
 *
 TODO to start to program. this program are wrote base on MVC structure
 */
package edu.nju.main;

 
import edu.nju.controller.impl.MenuControllerImpl;
import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.controller.service.MenuControllerService;
import edu.nju.model.impl.ChessBoardModelImpl;
import edu.nju.model.impl.GameModelImpl;
import edu.nju.model.impl.ParameterModelImpl;
import edu.nju.model.impl.StatisticModelImpl;
import edu.nju.view.MainFrame;
import edu.nju.view.MarkedMineLabel;

public class JMineSweeper {

	static MenuControllerService menuController = new MenuControllerImpl();
	public static void main(String[] args) {
		
		MainFrame ui = new MainFrame();
		StatisticModelImpl statisticModel = new StatisticModelImpl(); //控制统计数据
 		ParameterModelImpl mineNumberModel = new ParameterModelImpl();//参数model 剩余雷数等
 		ChessBoardModelImpl mineBoardModel = new ChessBoardModelImpl(mineNumberModel);//初始化棋盘 随机放置雷
		GameModelImpl gameModel = new GameModelImpl(statisticModel,mineBoardModel);	//gamemodel 控制游戏状态	
 		
		gameModel.addObserver(ui);
 		mineNumberModel.addObserver(ui.getMineNumberLabel());
 		mineNumberModel.addObserver(ui.getMarkedMineLabel());
 		mineBoardModel.addObserver(ui.getMineBoard());
 		
 		OperationQueue operationQueue = new OperationQueue(mineBoardModel, gameModel);
 		Thread operationThread = new Thread(operationQueue);
 		operationThread.start();
// 		operationQueue.frame = ui;
 		
 	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {   
			e.printStackTrace();   
		}
 		menuController.startGame();
	}
}

