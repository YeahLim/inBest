import { GroupSetting } from "./../type/GroupSetting";
import { instanceWithAuth } from "./interceptors";
import userStore from "../store/userStore";
import {
  SearchUserList,
  MyGroupList,
  WaitingGroupDetail,
  InProgressGroupDetail,
  JoinableGroupList,
  JoinableGroupDetail,
  InvestingStatusList,
} from "../type/Group";
import { ApiSuccessMessage } from "../type/ApiSuccessMessage";
// import { apiInstance } from ".";

// TODO: 테스트용
const apiWithAuth = instanceWithAuth("invest-service/group");
// const api = apiInstance("invest-service/group");

export const getInvestingStatus = async (): Promise<InvestingStatusList> => {
  const { data } = await apiWithAuth.get("/status");
  return data;
};

export const createGroup = async (groupSetting: GroupSetting): Promise<ApiSuccessMessage> => {
  const { data } = await apiWithAuth.post("/", groupSetting);
  return data;
};

export const searchUser = async (nickname: string): Promise<SearchUserList> => {
  const { data } = await apiWithAuth.get("", { params: { keyword: nickname } });
  return data;
};

export const getMyGroupList = async (): Promise<MyGroupList> => {
  const { userInfo } = userStore.getState();
  const { data } = await apiWithAuth.get("/my-list", { params: { userNickname: userInfo?.nickname } });
  return data;
};

export const getWaitingGroupDetail = async (simulationSeq: number): Promise<WaitingGroupDetail> => {
  const { data } = await apiWithAuth.get("/details", { params: { simulationSeq, progressState: "waiting" } });
  return data;
};

export const getInProgressGroupDetail = async (simulationSeq: number): Promise<InProgressGroupDetail> => {
  const { data } = await apiWithAuth.get("/details", { params: { simulationSeq, progressState: "inprogress" } });
  return data;
};

export const getJoinableGroupList = async (): Promise<JoinableGroupList> => {
  const { userInfo } = userStore.getState();
  const { data } = await apiWithAuth.get("/joinable-list", {
    params: { userNickname: userInfo?.nickname },
  });
  return data;
};

export const getJoinableGroupDetail = async (simulationSeq: number): Promise<JoinableGroupDetail> => {
  const { data } = await apiWithAuth.get("/details", { params: { simulationSeq, progressState: "waiting" } });
  return data;
};

export const joinGroup = async (simulationSeq: number): Promise<ApiSuccessMessage> => {
  const { userInfo } = userStore.getState();
  const { data } = await apiWithAuth.post("/join", { simulationSeq, userSeq: userInfo?.seq });
  return data;
};

export const exitGroup = async (simulationSeq: number): Promise<ApiSuccessMessage> => {
  const { userInfo } = userStore.getState();
  const { data } = await apiWithAuth.post("/exit", { simulationSeq, userSeq: userInfo?.seq });
  return data;
};

export const startInvesting = async (simulationSeq: number): Promise<ApiSuccessMessage> => {
  const { data } = await apiWithAuth.post("/start", { simulationSeq });
  return data;
};