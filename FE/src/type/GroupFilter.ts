export type Period = number[];
export type SeedMoney = number[];
export interface GroupFilter {
  period: Period;
  seedMoney: SeedMoney;
  meanTier: number[];
}
