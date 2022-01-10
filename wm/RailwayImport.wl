(* ::Package:: *)

BeginPackage["RailwayImport`"]


(* ::Text:: *)
(*\:041e\:0431\:044a\:044f\:0432\:043b\:0435\:043d\:0438\:0435 \:0444\:0443\:043d\:043a\:0446\:0438\:0439 \:043f\:0430\:043a\:0435\:0442\:0430*)


importTrackData;
importSwitchData;
importTrainData;
readLocomotive;


(* ::Text:: *)
(*\:0421\:043e\:0437\:0434\:0430\:0451\:043c \:043a\:043e\:043d\:0442\:0435\:043a\:0441\:0442, \:0432 \:043a\:043e\:0442\:043e\:0440\:043e\:043c \:0434\:0430\:0434\:0438\:043c \:043e\:043f\:0440\:0435\:0434\:0435\:043b\:0435\:043d\:0438\:0435 \:0444\:0443\:043d\:043a\:0446\:0438\:044f\:043c*)


Begin["Private`"]


Needs["JLink`"]


Clear[readTrack]
readTrack::"usage"="\:0421\:043e\:0437\:0434\:0430\:0451\:043c java \:043e\:0431\:044a\:0435\:043a\:0442 \:0436\:0435\:043b\:0435\:0437\:043d\:043e\:0434\:043e\:0440\:043e\:0436\:043d\:043e\:0433\:043e \:043f\:0443\:0442\:0438 \:0438 \:0441\:043e\:0445\:0440\:0430\:043d\:044f\:0435\:0442 \:0435\:0433\:043e \:0432\:043e \:0432\:043d\:0443\:0442\:0440\:0435\:043d\:043d\:044e \:0431\:0430\:0437\:0443 \:043a\:043b\:0430\:0441\:0441\:0430. ";
readTrack[{trackName_,wagonesCapacity_,switchStart_,switchEnd_,lengthMeters_}]:=JLink`JavaNew["station.immovable.RailwayTrack",trackName,wagonesCapacity,switchStart,switchEnd,lengthMeters]


Clear[importTrackData]
importTrackData::"usage"="\:0418\:043c\:043f\:043e\:0440\:0442\:0438\:0440\:0443\:0435\:0442 \:0434\:0430\:043d\:043d\:044b\:0435 \:043e \:0436\:0435\:043b\:0435\:0437\:043d\:043e\:0434\:043e\:0440\:043e\:0436\:043d\:044b\:0445 \:043f\:0443\:0442\:044f\:0445 \:0438\:0437 XLSX \:0444\:0430\:0439\:043b\:0430";
importTrackData[filePath_String]:=With[{trackSheet=Rest@Import[filePath,{"Data",1}]},readTrack/@MapAt[Round,trackSheet[[All,{1,3,4,5,2}]]/.""->0,{All,-2;;}]]


Clear[readSwitch]
readSwitch::"usage"="\:0421\:043e\:0437\:0434\:0430\:0451\:043c java \:043e\:0431\:044a\:0435\:043a\:0442 \:0436\:0435\:043b\:0435\:0437\:043d\:043e\:0434\:043e\:0440\:043e\:0436\:043d\:043e\:0439 \:0441\:0442\:0440\:0435\:043b\:043a\:0438.";
readSwitch[{from_String,to_String,type_String}]:=JLink`JavaNew["station.immovable.RailwaySwitch",from,to,type]


Clear[readTrackConnectionType]
readTrackConnectionType::"usage"="\:0421\:0447\:0438\:0442\:044b\:0432\:0430\:0435\:0442 \:0442\:0438\:043f \:0441\:0432\:044f\:0437\:0438 \:043c\:0435\:0436\:0434\:0443 \:043f\:0443\:0442\:044f\:043c\:0438";
readTrackConnectionType[{{s_,_},{s_,_}}]:="START_TO_START"
readTrackConnectionType[{{s_,_},{_,s_}}]:="START_TO_END"
readTrackConnectionType[{{_,s_},{_,s_}}]:="END_TO_END"
readTrackConnectionType[_]:="END_TO_START"


Clear[importSwitchData]
importSwitchData::"usage"="\:0418\:043c\:043f\:043e\:0440\:0442\:0438\:0440\:0443\:0435\:0442 \:0434\:0430\:043d\:043d\:044b\:0435 \:043e \:0436\:0435\:043b\:0435\:0437\:043d\:043e\:0434\:043e\:0440\:043e\:0436\:043d\:044b\:0445 \:0441\:0442\:0440\:0435\:043b\:043a\:0430\:0445 \:0438\:0437 XLSX \:0444\:0430\:0439\:043b\:0430";
importSwitchData[filePath_String]:=Module[
{rules,switchSheet,trackSheet,types},
trackSheet=Rest@Import[filePath,{"Data",1}];
switchSheet=Rest@Import[filePath,{"Data",2}];
rules=trackSheet[[All,{1,3,4}]]/.{name_,s1_,s2_}:>Rule[name,{s1,s2}];
types=readTrackConnectionType/@(switchSheet/.rules);
readSwitch/@MapThread[Append,{switchSheet,types}]
]


Clear[readTrain]
readTrain[{_,_,railcar_Integer,trackName_String,offsetStart_Integer}]:=JLink`JavaNew["station.movable.RailwayTrain",railcar,0,offsetStart,trackName,False]


Clear[importTrainData]
importTrainData::"usage"="\:0418\:043c\:043f\:043e\:0440\:0442\:0438\:0440\:0443\:0435\:0442 \:0434\:0430\:043d\:043d\:044b\:0435 \:043e \:0432\:0430\:0433\:043e\:043d\:0430\:0445 \:0438\:0437 XLSX \:0444\:0430\:0439\:043b\:0430";
importTrainData[filePath_String]:=With[{trainSheet=Rest@Import[filePath,{"Data",3}]},readTrain/@MapAt[Round,DeleteCases[trainSheet,{"","","","",""}]/."NULL"->0,{All,{2,3,5}}]]


Clear[readLocomotive]
readLocomotive::"usage"="\:0421\:043e\:0437\:0434\:0430\:0451\:0442 java \:043e\:0431\:044a\:0435\:043a\:0442 \:043b\:043e\:043a\:043e\:043c\:043e\:0442\:0438\:0432\:0430.";
readLocomotive[{time_Integer,speed_Integer,offsetStart_Integer,trackName_String}]:=JLink`JavaNew["station.movable.ShuntingLocomotive",time,speed,offsetStart,trackName]


(* ::Text:: *)
(*\:0417\:0430\:043a\:0440\:044b\:0432\:0430\:0435\:043c \:043a\:043e\:043d\:0442\:0435\:043a\:0441\:0442*)


End[]


EndPackage[]
