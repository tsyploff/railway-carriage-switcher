(* ::Package:: *)

BeginPackage["Actions`"]


(* ::Text:: *)
(*\:041e\:0431\:044a\:044f\:0432\:043b\:0435\:043d\:0438\:0435 \:0444\:0443\:043d\:043a\:0446\:0438\:0439 \:043f\:0430\:043a\:0435\:0442\:0430*)


createCoupleWagonsAction;
createCoupleLocomotiveAction;
createMoveToAnotherTrackAction;
createSwitchMovementDirectionAction;
createUncoupleLocomotiveAction;
createUncoupleWagonsAction;


(* ::Text:: *)
(*\:0421\:043e\:0437\:0434\:0430\:0451\:043c \:043a\:043e\:043d\:0442\:0435\:043a\:0441\:0442, \:0432 \:043a\:043e\:0442\:043e\:0440\:043e\:043c \:0434\:0430\:0434\:0438\:043c \:043e\:043f\:0440\:0435\:0434\:0435\:043b\:0435\:043d\:0438\:0435 \:0444\:0443\:043d\:043a\:0446\:0438\:044f\:043c*)


Begin["Private`"]


Needs["JLink`"]


Clear[createCoupleWagonsAction]
createCoupleWagonsAction::"usage"="\:0421\:043e\:0437\:0434\:0430\:0451\:0442 \:043a\:043e\:043c\:0430\:043d\:0434\:0443 \:043d\:0430 \:0441\:043e\:0435\:0434\:0438\:043d\:0435\:043d\:0438\:0435 \:0434\:0432\:0443\:0445 \:0441\:043e\:0441\:0442\:0430\:0432\:043e\:0432 \:0441 \:0438\:0434\:0435\:043d\:0442\:0438\:0444\:0438\:043a\:0430\:0442\:043e\:0440\:0430\:043c\:0438 leftId \:0438 rightId \:0432 \:043c\:043e\:043c\:0435\:043d\:0442 \:0432\:0440\:0435\:043c\:0435\:043d\:0438 time.";
createCoupleWagonsAction[time_Integer,leftId_Integer,rightId_Integer]:=JLink`JavaNew["actions.CoupleWagons",time,leftId,rightId]


Clear[createCoupleLocomotiveAction]
createCoupleLocomotiveAction::"usage"="\:0421\:043e\:0437\:0434\:0430\:0451\:0442 \:043a\:043e\:043c\:0430\:043d\:0434\:0443 \:043d\:0430 \:043f\:0440\:0438\:0441\:043e\:0435\:0434\:0438\:043d\:0435\:043d\:0438\:0435 \:043b\:043e\:043a\:043e\:043c\:043e\:0442\:0438\:0432\:0430 locomotiveId \:043a \:0441\:043e\:0441\:0442\:0430\:0432\:0443 trainId \:0432 \:043c\:043e\:043c\:0435\:043d\:0442 \:0432\:0440\:0435\:043c\:0435\:043d\:0438 time.";
createCoupleLocomotiveAction[time_Integer,trainId_Integer,locomotiveId_Integer]:=JLink`JavaNew["actions.CoupleLocomotive",time,trainId,locomotiveId]


Clear[createMoveToAnotherTrackAction]
createMoveToAnotherTrackAction::"usage"="\:0421\:043e\:0437\:0434\:0430\:0451\:0442 \:043a\:043e\:043c\:0430\:043d\:0434\:0443 \:043d\:0430 \:043f\:0435\:0440\:0435\:043c\:0435\:0449\:0435\:043d\:0438\:0435 \:043b\:043e\:043a\:043e\:043c\:043e\:0442\:0438\:0432\:0430 locomotiveId \:043d\:0430 \:0441\:043e\:0435\:0434\:0438\:043d\:0451\:043d\:043d\:044b\:0439 \:043f\:0440\:044f\:043c\:043e\:0439 \:0441\:0442\:0440\:0435\:043b\:043a\:043e\:0439 railwaySwitch \:043f\:0443\:0442\:044c \:043d\:0430 \:0440\:0430\:0441\:0441\:0442\:043e\:044f\:043d\:0438\:0435 offsetStart \:043e\:0442 \:043d\:0430\:0447\:0430\:043b\:044c\:043d\:043e\:0439 \:0441\:0442\:0440\:0435\:043b\:043a\:0438 \:044d\:0442\:043e\:0433\:043e \:043f\:0443\:0442\:0438 \:0434\:043e \:0446\:0435\:043d\:0442\:0440\:0430 \:043b\:043e\:043a\:043e\:043c\:043e\:0442\:0438\:0432\:0430 \:0441\:043e \:0441\:043a\:043e\:0440\:043e\:0441\:0442\:044c\:044e speed \:0432 \:043c\:043e\:043c\:0435\:043d\:0442 \:0432\:0440\:0435\:043c\:0435\:043d\:0438 time.";
createMoveToAnotherTrackAction[time_Integer,locomotiveId_Integer,railwaySwitch_,offsetStart_Integer,speed_Integer]:=JLink`JavaNew["actions.MoveToAnotherTrack",time,locomotiveId,railwaySwitch,offsetStart,speed]


Clear[createSwitchMovementDirectionAction]
createSwitchMovementDirectionAction::"usage"="\:0421\:043e\:0437\:0434\:0430\:0451\:0442 \:043a\:043e\:043c\:0430\:043d\:0434\:0443 \:043d\:0430 \:0441\:043c\:0435\:043d\:0443 \:043b\:043e\:043a\:043e\:043c\:043e\:0442\:0438\:0432\:043e\:043c locomotiveId \:043d\:0430\:043f\:0440\:0430\:0432\:043b\:0435\:043d\:0438\:044f \:0441\:0432\:043e\:0435\:0433\:043e \:0434\:0432\:0438\:0436\:0435\:043d\:0438\:044f \:043d\:0430 \:043e\:0431\:0440\:0430\:0442\:043d\:043e\:0435 \:0432 \:043c\:043e\:043c\:0435\:043d\:0442 \:0432\:0440\:0435\:043c\:0435\:043d\:0438 time.";
createSwitchMovementDirectionAction[time_Integer,locomotiveId_Integer]:=JLink`JavaNew["actions.SwitchMovementDirection",time,locomotiveId]


Clear[createUncoupleLocomotiveAction]
createUncoupleLocomotiveAction::"usage"="\:0421\:043e\:0437\:0434\:0430\:0451\:0442 \:043a\:043e\:043c\:0430\:043d\:0434\:0443 \:043d\:0430 \:043e\:0442\:0441\:043e\:0435\:0434\:0438\:043d\:0435\:043d\:0438\:044f \:0441\:043e\:0441\:0442\:0430\:0432\:0430 \:043e\:0442 \:043b\:043e\:043a\:043e\:043c\:043e\:0442\:0438\:0432\:0430 locomotiveId \:0432 \:043c\:043e\:043c\:0435\:043d\:0442 \:0432\:0440\:0435\:043c\:0435\:043d\:0438 time.";
createUncoupleLocomotiveAction[time_Integer,locomotiveId_Integer]:=JLink`JavaNew["actions.UncoupleLocomotive",time,locomotiveId]


Clear[createUncoupleWagonsAction]
createUncoupleWagonsAction::"usage"="\:0421\:043e\:0437\:0434\:0430\:0451\:0442 \:043a\:043e\:043c\:0430\:043d\:0434\:0443 \:043d\:0430 \:043e\:0442\:0441\:043e\:0435\:0434\:0438\:043d\:0435\:043d\:0438\:0435 leftCount \:0432\:0430\:0433\:043e\:043d\:043e\:0432 \:0441 \:043b\:0435\:0432\:043e\:0433\:043e \:043a\:043e\:043d\:0446\:0430 \:0441\:043e\:0441\:0442\:0430\:0432\:0430 trainId \:0432 \:043c\:043e\:043c\:0435\:043d\:0442 \:0432\:0440\:0435\:043c\:0435\:043d\:0438 time.";
createUncoupleWagonsAction[time_Integer,trainId_Integer,leftCount_Integer]:=JLink`JavaNew["actions.UncoupleWagons",time,trainId,leftCount]


(* ::Text:: *)
(*\:0417\:0430\:043a\:0440\:044b\:0432\:0430\:0435\:043c \:043a\:043e\:043d\:0442\:0435\:043a\:0441\:0442*)


End[]


EndPackage[]
