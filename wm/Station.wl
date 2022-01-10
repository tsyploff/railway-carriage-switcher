(* ::Package:: *)

BeginPackage["Station`"]


(* ::Text:: *)
(*\:041e\:0431\:044a\:044f\:0432\:043b\:0435\:043d\:0438\:0435 \:0444\:0443\:043d\:043a\:0446\:0438\:0439 \:043f\:0430\:043a\:0435\:0442\:0430*)


StationModel;
stationModelAddTrains;
stationModelAddLocomotives;
stationModelExecute;


(* ::Text:: *)
(*\:0421\:043e\:0437\:0434\:0430\:0451\:043c \:043a\:043e\:043d\:0442\:0435\:043a\:0441\:0442, \:0432 \:043a\:043e\:0442\:043e\:0440\:043e\:043c \:0434\:0430\:0434\:0438\:043c \:043e\:043f\:0440\:0435\:0434\:0435\:043b\:0435\:043d\:0438\:0435 \:0444\:0443\:043d\:043a\:0446\:0438\:044f\:043c*)


Begin["Private`"]


Needs["JLink`"]


Clear[StationModel]
StationModel::"usage"="\:0421\:043e\:0437\:0434\:0430\:0451\:0442 \:043c\:043e\:0434\:0435\:043b\:044c \:0441\:0442\:0430\:043d\:0446\:0438\:0438";
StationModel[]:=JLink`JavaNew["station.Station"]


Clear[stationModelAddTrains]
stationModelAddTrains::"usage"="\:0417\:0430\:043f\:043e\:043b\:043d\:044f\:0435\:0442 \:0441\:0442\:0430\:043d\:0446\:0438\:044e \:0441\:043e\:0441\:0442\:0430\:0432\:0430\:043c\:0438";
stationModelAddTrains[station_,trains_]:=Module[{model=station},Do[model@addTrain[train],{train,trains}];model]


Clear[stationModelAddLocomotives]
stationModelAddLocomotives::"usage"="\:0417\:0430\:043f\:043e\:043b\:043d\:044f\:0435\:0442 \:0441\:0442\:0430\:043d\:0446\:0438\:044e \:043b\:043e\:043a\:043e\:043c\:043e\:0442\:0438\:0432\:0430\:043c\:0438";
stationModelAddLocomotives[station_,locomotives_]:=Module[{model=station},Do[model@addLocomotive[locomotive],{locomotive,locomotives}];model]


Clear[stationModelExecute]
stationModelExecute::"usage"="\:0412\:044b\:043f\:043e\:043b\:043d\:044f\:0435\:0442 \:0437\:0430\:043f\:0440\:043e\:0441, \:0432\:043e\:0437\:0432\:0440\:0430\:0449\:0430\:0435\:0442 \:043f\:0430\:0440\:0443 {station, log}, \:0433\:0434\:0435 log \[Dash] \:043b\:043e\:0433\:0438 \:0440\:0430\:0431\:043e\:0442\:044b";
stationModelExecute[station_,action_]:=Module[
{model=station,logs},
logs=model@execute[action];
{model,Table[logs@get[i],{i,0,logs@size[]-1}]}
]


(* ::Text:: *)
(*\:0417\:0430\:043a\:0440\:044b\:0432\:0430\:0435\:043c \:043a\:043e\:043d\:0442\:0435\:043a\:0441\:0442*)


End[]


EndPackage[]
